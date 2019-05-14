package com.spring5.conf;

import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by blockWilling on 2019/4/29.
 * 绑定application.properties中的属性
 * 实际是通过{@link ConfigurationPropertiesBindingPostProcessor}在实例化之前去绑定properties
 * 相比于{@link org.springframework.beans.factory.annotation.Value},更加灵活一次性绑定，可以注入复杂对象，但是不支持#{}的spell表达式
 */
@ConfigurationProperties(prefix="my")
@Validated
public class MyConfig {
    @Min(100)
    private int initNum=111;

    private List<String> servers = new ArrayList<String>();
//必须要有这个方法
    public List<String> getServers() {
        return this.servers;
    }

//    public int getInitNum() {
//        return initNum;
//    }

    /**
     * 可以自定义set方法的逻辑
     * @param initNum
     */
    public void setInitNum(String initNum) {
        this.initNum = Integer.parseInt(initNum.replace("prefix",""));
    }
}
