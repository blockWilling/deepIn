package com.spring5.servlet;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FrameworkServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 康金 on 2019/1/27.
 */
@Component
@WebServlet(loadOnStartup = 2,urlPatterns = {"/simple"},initParams = {
        @WebInitParam(name = "noLoginPaths", value = "index.jsp;fail.jsp;/LoginServlet")
})
public class SimpleServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("com.spring5.servlet.SimpleServlet.init");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("");
    }
}
