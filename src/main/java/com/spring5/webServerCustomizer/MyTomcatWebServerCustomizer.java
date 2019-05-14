package com.spring5.webServerCustomizer;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

/**
 * 自定义配置{@link WebServerFactory}
 * Created by blockWilling on 2019/5/13.
 */
@Component
public class MyTomcatWebServerCustomizer
        implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        // customize the factory here
        factory.setPort(8888);
    }
}
