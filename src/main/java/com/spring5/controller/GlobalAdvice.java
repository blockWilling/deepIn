package com.spring5.controller;

import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * On startup, the infrastructure classes for @RequestMapping and @ExceptionHandler methods detect Spring beans of type @ControllerAdvice and then apply their methods at runtime.
 * Global @ExceptionHandler methods (from a @ControllerAdvice) are applied !! after !! local ones (from the @Controller).
 * By contrast, global @ModelAttribute and @InitBinder methods are applied !! before !! local ones.
 */
@ControllerAdvice
public class GlobalAdvice {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor();
       System.out.println("ControllerAdvice");
    }

}
