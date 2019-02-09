package com.spring5.anno;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * Created by 康金 on 2019/2/1.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface MyComponent {
    String value() default "";
}
