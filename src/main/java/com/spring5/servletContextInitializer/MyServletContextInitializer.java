package com.spring5.servletContextInitializer;

import com.spring5.servlet.SimpleServlet;
import com.spring5.servlet.filter.simpleFilter;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.boot.web.servlet.*;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.DispatcherType;
import java.util.EnumSet;

/**
 * 方式二：除了使用 {@link RegistrationBean}，添加传统servlet，filter的另一种方式
 * 方式三：
 *  {@link ServletComponentScan}去扫描@WebServlet, @WebFilter, and @WebListener
 *  import了{@link ServletComponentScanRegistrar}({是一个@link ImportBeanDefinitionRegistrar})
 *  所以会在初始化的时候遍历所有{@link ImportBeanDefinitionRegistrar}去调用
 *  {@link ImportBeanDefinitionRegistrar#registerBeanDefinitions(org.springframework.core.type.AnnotationMetadata, org.springframework.beans.factory.support.BeanDefinitionRegistry)}
 *  的时候调用到,去注册了{@link servletComponentRegisteringPostProcessor}的BD，而这个{@link BeanFactoryPostProcessor}
 *  首先在静态代码块中初始化了三个hadler：{@link WebServletHandler},{@link WebFilterHandler},{@link WebListenerHandler}
 *  在{@link ServletComponentRegisteringPostProcessor#postProcessBeanFactory(org.springframework.beans.factory.config.ConfigurableListableBeanFactory)}
 *  方法中如果是扫包注册的BD（即是{@link ScannedGenericBeanDefinition}）调用这三个handler的自己的实现doHandle方法,最后注册的也是XXXRegistrationBean的类型
 */
@Component
public class MyServletContextInitializer implements ServletContextInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        ServletRegistration.Dynamic registration = servletContext.addServlet("SimpleServlet", new SimpleServlet());
        registration.setLoadOnStartup(2);
        registration.addMapping("/simple");
//        FilterRegistration.Dynamic simpleFilter = servletContext.addFilter("simpleFilter", new simpleFilter());
//        simpleFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),false,"/simple");


    }

}
