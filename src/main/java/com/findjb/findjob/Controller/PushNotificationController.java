package com.findjb.findjob.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.findjb.findjob.Fcm.FCMService;
import com.findjb.findjob.Request.FcmToken;

@RestController
public class PushNotificationController {
    // @Autowired
    // private FCMService fcmService;

    // @PostMapping("/notification")
    // public String sendSampleNotification(@RequestBody FcmToken pnsRequest) {
    //     return fcmService.pushNotification(pnsRequest);
    // }
}
