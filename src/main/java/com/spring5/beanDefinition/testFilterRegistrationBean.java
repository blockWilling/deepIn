package com.spring5.beanDefinition;

import com.spring5.servlet.SimpleServlet;
import com.spring5.servlet.filter.simpleFilter;
import org.springframework.boot.web.servlet.AbstractFilterRegistrationBean;
import org.springframework.boot.web.servlet.DynamicRegistrationBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.util.Assert;

import javax.servlet.ServletContext;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 与zuul整合的时候出现jar不匹配，暂时注释
 * Created by blockWilling on 2019/1/28.
 */
public class testFilterRegistrationBean
       // extends FilterRegistrationBean<simpleFilter>
{

    public testFilterRegistrationBean(simpleFilter filter, ServletRegistrationBean<?>[] servletRegistrationBeans) {
//        super(filter, servletRegistrationBeans);
//        Map<String, String> map=new HashMap<>();
//        map.put("test","te");
//        setInitParameters(map);
//        setUrlPatterns(Arrays.asList("/filter"));
    }
}
