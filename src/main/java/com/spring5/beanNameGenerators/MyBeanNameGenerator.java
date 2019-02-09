package com.spring5.beanNameGenerators;

import com.spring5.anno.MyComponent;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.beans.Introspector;
import java.util.Set;

/**
 * 自定义BeanNameGenerator，依据自己的规则返回beanName，比如这里的根据{@link MyComponent#value()}
 */
public class MyBeanNameGenerator implements BeanNameGenerator {
    private static final String MY_COMPONENT_ANNOTATION_CLASSNAME = "com.spring5.anno.MyComponent";

    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        if (definition instanceof AnnotatedBeanDefinition) {
        AnnotationMetadata amd =((AnnotatedBeanDefinition) definition).getMetadata();
        Set<String> types = amd.getAnnotationTypes();
        String beanName = null;
        for (String type : types) {
            if(type.equals(MY_COMPONENT_ANNOTATION_CLASSNAME)){
                Object value = AnnotationAttributes.fromMap(amd.getAnnotationAttributes(type, false)).get("value");
                if (value instanceof String) {
                    return (String) value;
                }
            }
        }}
        return buildDefaultBeanName(definition);
    }
    protected String buildDefaultBeanName(BeanDefinition definition) {
        String beanClassName = definition.getBeanClassName();
        Assert.state(beanClassName != null, "No bean class name set");
        String shortClassName = ClassUtils.getShortName(beanClassName);
        return Introspector.decapitalize(shortClassName);
    }
}
