package com.spring5.conf;

import com.spring5.entity.Person;
import com.spring5.handlerInterceptor.MyHandlerInterceptor;
import org.apache.catalina.core.ContainerBase;
import org.apache.catalina.servlets.DefaultServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.lang.Nullable;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.ViewResolverComposite;
import org.springframework.web.util.UrlPathHelper;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

/**
 * {@link EnableWebMvc}加入的 {@link DelegatingWebMvcConfiguration}通过
 *      {@link Autowired}的方式把我们的自定义配置类(从beanFactory中找 {@link WebMvcConfigurer})
 *      加入了 {@link WebMvcConfigurerComposite#delegates}中，用于handlerMapping，handlerAdapter的成员属性注入
 * {@link DelegatingWebMvcConfiguration}的父类 {@link WebMvcConfigurationSupport}
 *      则提供了handlerMapping，handlerAdapter的 {@link Bean} method注入方法和默认的一些converter等的注入
 *
 */
@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
    @Value("${spring.mvc.view.prefix}")
    private String jspPrefix;
    @Value("${spring.mvc.view.suffix}")
    private String jspSufix;
    @Override
    public void addFormatters(FormatterRegistry registry) {
        /**
         * 添加自定义的formatter
         * 在构造adapter的时候被调用，去加入 {@link GenericConversionService.Converters}
         */
        registry.addFormatter(new Formatter<Person>() {
            @Override
            public Person parse(String text, Locale locale) throws ParseException {
                Person person = new Person();
                person.setName(text);
                return person;
            }

            @Override
            public String print(Person object, Locale locale) {
                return null;
            }
        });
    }

    /**
     * 添加自定义拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyHandlerInterceptor());
    }

    /**
     * 添加 {@link InternalResourceViewResolver}到 {@link ViewResolverComposite#viewResolvers}属性中，
     * 用于视图解析
     * @param registry
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp(jspPrefix,jspSufix);
    }

    /**
     *激活 {@link DefaultServletHttpRequestHandler}，用于处理无法mapping到Controller的path
     * 需要设置执行的servletName，根据这个servletName,调用 {@link ContainerBase#findChild(java.lang.String)}
     * 去查找servletContainer中的servlet,一般情况下不会去设置(默认走{@link DefaultServlet})，仅仅用于静态资源访问
     * 只有这里执行了 {@link DefaultServletHandlerConfigurer#enable(java.lang.String)}方法，才会：
     *      1.在 {@link DispatcherServlet#getHandler(javax.servlet.http.HttpServletRequest)}的时候，才会多出一个 {@link HandlerMapping}
     *      原理参考 {@link WebMvcConfigurationSupport#defaultServletHandlerMapping()}的 {@link Bean}方法
     *      2.在 {@link DefaultServletHttpRequestHandler#setServletContext(javax.servlet.ServletContext)}中设置默认走{@link DefaultServlet}
     *
     * 设置{@link WebMvcProperties#staticPathPattern}可以设置resource的路径，默认/**
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//    configurer.enable();
    }

    /**
     * 把根据传入的pathPatterns构建的 {@link ResourceHandlerRegistration}对象，加入 {@link ResourceHandlerRegistry#registrations}
     * 如果 {@link ResourceHandlerRegistry#registrations}为empty则不会创建用于resource映射的 {@link SimpleUrlHandlerMapping}
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/js/**").addResourceLocations("/js/");
    }

    /**
     * 设置 {@link UrlPathHelper#removeSemicolonContent}为false，去开启 {@link MatrixVariable}的功能
     * @param configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        urlPathHelper.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(urlPathHelper);
    }

    /**
     * 配置异步请求的mapping方法返回 {@link java.util.concurrent.Callable}的时候，使用的 {@link java.util.concurrent.Executor}
     * 设置到 {@link AsyncSupportConfigurer#taskExecutor}成员属性中
     *
     * @param configurer
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(new SimpleAsyncTaskExecutor());
    }

    /**
     * CORS配置
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/jsp/**")
                .allowedOrigins("http://domain2.com")
                .allowedMethods("PUT", "DELETE")
                .allowedHeaders("header1", "header2", "header3")
                .exposedHeaders("header1", "header2")
                .allowCredentials(true).maxAge(3600);

        // Add more mappings...
    }

    /**
     * 根据http://localhost:9090/hello.zz中的文件扩展名，即“.zz”匹配 {@link ContentNegotiationConfigurer#mediaTypes}
     * 在 {@link WebMvcConfigurationSupport#getDefaultMediaTypes()}中定义了默认的mediaType匹配扩展名
     * @param configurer
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.mediaType("zz", MediaType.TEXT_MARKDOWN);
    }

    /**
     * 在 {@link DispatcherServlet#handlerMappings}添加一个 {@link HandlerMapping}
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
    }
    @Nullable
    @Override
    public Validator getValidator() {
        return null;
    }
}
