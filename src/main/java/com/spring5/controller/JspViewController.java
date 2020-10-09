package com.spring5.controller;

import com.spring5.conf.MvcConfig;
import com.spring5.entity.Person;
import com.spring5.exception.MvcException;
import com.spring5.validation.PersonValidator;
import org.springframework.beans.PropertyEditorRegistrySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.ModelAttributeMethodProcessor;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.awt.print.Book;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 在pom.xml中注意需要将tomcat-embed-jasper的<scope></scope>设置为compile，
 * 否则使用 {@link DefaultServletHttpRequestHandler}完成jsp路径映射之后，无法渲染jsp，只会当成文本输出在浏览器上
 */
@Controller
@RequestMapping("/jsp")
public class JspViewController {

    @Autowired
    PersonValidator personValidator;

    /**
     * 配置了{@link RequestMapping#produces}, 就不用遍历HttpMessageConverter，获取应用端可以接受的mediaType
         * 配置了{@link RequestMapping#consumes()}, 会在getHandler的流程中校验，
     * ===consumes===
     * protected HandlerMethod lookupHandlerMethod(String lookupPath, HttpServletRequest request) throws Exception {
     * 		List<Match> matches = new ArrayList<>();
     * 		List<T> directPathMatches = this.mappingRegistry.getMappingsByUrl(lookupPath);
     * 		if (directPathMatches != null) {
     * 			addMatchingMappings(directPathMatches, matches, request);
     *                }
     * 		if (matches.isEmpty()) {
     * 			// No choice but to go through all mappings...
     * 			addMatchingMappings(this.mappingRegistry.getMappings().keySet(), matches, request);
     *        }
     *
     * 		if (!matches.isEmpty()) {
     * 	//	getMappingComparator(request)获取了注解的consume等属性
     * 			Comparator<Match> comparator = new MatchComparator(getMappingComparator(request));
     * 		//使用compare去排序
     * 			matches.sort(comparator);
     * 			if (logger.isTraceEnabled()) {
     * 				logger.trace("Found " + matches.size() + " matching mapping(s) for [" + lookupPath + "] : " + matches);
     *            }
     * 			Match bestMatch = matches.get(0);
     * 			if (matches.size() > 1) {
     * 				if (CorsUtils.isPreFlightRequest(request)) {
     * 					return PREFLIGHT_AMBIGUOUS_MATCH;
     *                }
     * 				Match secondBestMatch = matches.get(1);
     * 				if (comparator.compare(bestMatch, secondBestMatch) == 0) {
     * 					Method m1 = bestMatch.handlerMethod.getMethod();
     * 					Method m2 = secondBestMatch.handlerMethod.getMethod();
     * 					throw new IllegalStateException("Ambiguous handler methods mapped for HTTP path '" +
     * 							request.getRequestURL() + "': {" + m1 + ", " + m2 + "}");
     *                }
     *            }
     * 			handleMatch(bestMatch.mapping, lookupPath, request);
     * 			return bestMatch.handlerMethod;
     *        }
     * 		else {
     * 			return handleNoMatch(this.mappingRegistry.getMappings().keySet(), lookupPath, request);
     *        }* 	}
     *
     *
     * ==consumes==
     * 实际是在 {@link RequestMappingInfo#getMatchingCondition(javax.servlet.http.HttpServletRequest)}中
     * 配置了{@link RequestMapping#params()}和配置了{@link RequestMapping#headers()}同上判断
     *
     * 在 {@link RequestMappingInfo#getMatchingCondition(javax.servlet.http.HttpServletRequest)}中
     * 使用的XXXCondition的RequestMappingInfo的成员变量，都是容器启动的时候放在{@link AbstractHandlerMethodMapping#mappingRegistry}
     * 中的{@link AbstractHandlerMethodMapping.MappingRegistry#mappingLookup}的key，即{@link RequestMappingInfo}
     * <p>
     * <p>
     * {@link MatrixVariable}的使用
     *
     * @param q1
     * @param matrixVars
     * @param ownerId
     * @param petMatrixVars
     * @return
     * @throws MvcException
     */
    @GetMapping(value = "/owners/{ownerId}/pets/{petId}", produces = MediaType.TEXT_PLAIN_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            params = "a=1",
            headers = "b=2"
    )
//http://localhost:9090/jsp/owners/42;q=11;r=12/pets/21;q=22;s=23?a=1
    public String hello(@PathVariable int ownerId,
                        @MatrixVariable MultiValueMap<String, String> matrixVars,
                        @MatrixVariable(pathVar = "petId") MultiValueMap<String, String> petMatrixVars,
                        @MatrixVariable(name = "q", pathVar = "ownerId") int q1) throws MvcException {
//        throw new MvcException();
        return "hello";
    }

    @PostMapping("/postFile")
    public String handle(MultipartFile file) {
        // ...
        return "hello";
    }

    /**
     * {@link CrossOrigin#origins()}属性与请求头的"Origin"value去匹配
     *
     * @param date
     * @return
     */
    @CrossOrigin(origins = "http://domain2.com")
    @GetMapping("/hello")
    public String hello(Date date,@ModelAttribute @NotNull @Valid Person p) {
        return "hello";
    }

    /**
     * ETag的头部属性使用(可以单独使用)，访问静态文件也有此效果
     * 但是cacheControl想要生效，需要同时设置lastModified
     *
     * @return
     */

    @GetMapping("/book")
    public ResponseEntity<String> showBook() {

        return ResponseEntity
                .ok()
                .cacheControl(CacheControl.maxAge(30, TimeUnit.DAYS))
                .lastModified(System.currentTimeMillis()-10000)
//               .eTag("123456") // lastModified is also available
                .body("eeetag");
    }

    /**
     * 使用 {@link InitBinder}加入 {@link DataBinder#typeConverter}的 父类{@link PropertyEditorRegistrySupport#customEditors}属性
     * 相比于 {@link MvcConfig#addFormatters(org.springframework.format.FormatterRegistry)}中加入的converter，优先级更高
     *
     * 添加validator：用于入参校验，默认在{@link ModelAttributeMethodProcessor#resolveArgument(org.springframework.core.MethodParameter, org.springframework.web.method.support.ModelAndViewContainer, org.springframework.web.context.request.NativeWebRequest, org.springframework.web.bind.support.WebDataBinderFactory)}
     * 和 {@link RequestResponseBodyMethodProcessor#resolveArgument(org.springframework.core.MethodParameter, org.springframework.web.method.support.ModelAndViewContainer, org.springframework.web.context.request.NativeWebRequest, org.springframework.web.bind.support.WebDataBinderFactory)}
     * 中使用。默认有一个validator，存放了默认的验证注解的对应校验类
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addCustomFormatter(new DateFormatter("yyyy"));
        //用于入参校验,默认只在org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor.resolveArgument() 和
        binder.addValidators(personValidator);
    }

}
