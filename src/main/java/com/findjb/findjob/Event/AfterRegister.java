package com.findjb.findjob.Event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;

import com.findjb.findjob.Request.LoginRequest;

public class AfterRegister implements ApplicationEventPublisher {

    @Override
    public void publishEvent(Object event) {
        // super(event);
    }

}
