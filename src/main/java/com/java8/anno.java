package com.java8;

import java.lang.annotation.*;

/**
 * Created by blockWilling on 2019/1/24.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(annos.class)
public @interface anno {
    String value();
}
