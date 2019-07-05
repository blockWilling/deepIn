package com.spring5.servlet.filter;

import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
//import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 与zuul整合的时候出现jar不匹配，暂时注释
 * Created by blockWilling on 2019/1/29.
 */
//@Component
@WebFilter(urlPatterns = {"/simp"},initParams = {
        @WebInitParam(name = "6", value = "in6p;/LoginServlet")
})
public class simpleFilter
//        extends HttpFilter
{
    public simpleFilter() {
        System.out.println();
    }

//    @Override
//    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        super.doFilter(request, response, chain);
//    }
//
//    @Override
//    public void destroy() {
//
//    }
}