package com.spring5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * Created by blockWilling on 2019/2/22.
 */
@Controller
@RequestMapping
public class AsyncController {
    @RequestMapping(value = "/asynctask1", method = RequestMethod.GET)
    public DeferredResult<ModelAndView> asyncTask() {
        DeferredResult<ModelAndView> deferredResult = new DeferredResult<ModelAndView>();
        System.out.println("/asynctask 调用！thread id is : " + Thread.currentThread().getId());
        new Thread(() -> {
            try {
                Thread.sleep(3000); //假设是一些长时间任务
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ModelAndView mv = new ModelAndView("async");
            deferredResult.setResult(mv);
        }).start();
        return deferredResult;
    }

    @RequestMapping(value = "/asynctask2", method = RequestMethod.GET)
    public Callable<ModelAndView> longTimeTask() {
        System.out.println("/longtimetask被调用 thread id is : " + Thread.currentThread().getId());
        Callable<ModelAndView> callable = () -> {
            Thread.sleep(3000); //假设是一些长时间任务
            ModelAndView mav = new ModelAndView("async");
            System.out.println("执行成功 thread id is : " + Thread.currentThread().getId());
            return mav;
        };
        return callable;
    }

    @GetMapping("/asynctask3")
    public ResponseBodyEmitter handle() {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        // Save the emitter somewhere..
        new Thread(() -> {
            try {
                emitter.send("Hello once");
                emitter.complete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        return emitter;
    }
}
