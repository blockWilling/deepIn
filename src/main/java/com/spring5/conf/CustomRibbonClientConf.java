package com.spring5.conf;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import com.spring5.anno.ExcludeFromComponentScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by blockWilling on 2019/5/17.
 */

@Configuration
//@ExcludeFromComponentScan
public class CustomRibbonClientConf {

    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
    }
   /* @Bean
    public ServerList<Server> ribbonServerList(IClientConfig config) {
        ConfigurationBasedServerList serverList = new ConfigurationBasedServerList();
        return serverList;
    }*/


}
