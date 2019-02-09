package com.spring5.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by 康金 on 2019/1/24.
 */
public class myEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public myEvent(Object source) {
        super(source);
    }
}
