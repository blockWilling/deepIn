package com.spring5.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;

/**
 * {@link ResponseStatus}是在。。。中处理的
 * {@link ResponseStatusExceptionResolver#doResolveException(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)}
 */
@Component
@ResponseStatus(code = HttpStatus.REQUEST_TIMEOUT,reason = "SB")
public class MvcException extends Exception {

}
