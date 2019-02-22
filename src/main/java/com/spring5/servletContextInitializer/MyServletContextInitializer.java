package com.spring5.servletContextInitializer;

import com.spring5.servlet.SimpleServlet;
import com.spring5.servlet.filter.simpleFilter;
import org.springframework.boot.web.servlet.RegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.HttpRequestHandlerServlet;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.EnumSet;

/**
 * 除了使用 {@link RegistrationBean}，添加传统servlet，filter的另一种方式
 */
@Component
public class MyServletContextInitializer implements ServletContextInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        ServletRegistration.Dynamic registration = servletContext.addServlet("SimpleServlet", new SimpleServlet());
        registration.setLoadOnStartup(2);
        registration.addMapping("/simple");
        FilterRegistration.Dynamic simpleFilter = servletContext.addFilter("simpleFilter", new simpleFilter());
        simpleFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),false,"/simple");


    }

}
