package com.spring5.servlet;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FrameworkServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 康金 on 2019/1/27.
 */
//@Component
@WebServlet(urlPatterns = {"/"},loadOnStartup = 2)
public class testFrameworkServlet extends FrameworkServlet {

    @Override
    protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
System.out.println("com.spring5.servlet.testFrameworkServlet.doService");
    }

    @Override
    protected void initFrameworkServlet() throws ServletException {

    System.out.println("com.spring5.servlet.testFrameworkServlet.initFrameworkServlet");}

}
