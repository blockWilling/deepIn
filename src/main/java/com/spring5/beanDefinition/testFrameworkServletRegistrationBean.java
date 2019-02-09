package com.spring5.beanDefinition;

import com.spring5.servlet.SimpleServlet;
import com.spring5.servlet.testFrameworkServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 康金 on 2019/1/28.
 */
public class testFrameworkServletRegistrationBean extends
        ServletRegistrationBean<SimpleServlet> {

    public testFrameworkServletRegistrationBean(SimpleServlet servlet) {

    }

    public testFrameworkServletRegistrationBean(SimpleServlet servlet, int in) {
        super(servlet, "/sim");
        setLoadOnStartup(in);
        Map<String, String> map=new HashMap<>();
        map.put("test","te");
        setInitParameters(map);
    }

    @Override
    public void setLoadOnStartup(int loadOnStartup) {
        super.setLoadOnStartup(loadOnStartup);
    }

    public testFrameworkServletRegistrationBean(SimpleServlet servlet, String... urlMappings) {
        super(servlet, urlMappings);
    }

    public testFrameworkServletRegistrationBean(SimpleServlet servlet, boolean alwaysMapUrl, String... urlMappings) {
        super(servlet, alwaysMapUrl, urlMappings);
    }


}
