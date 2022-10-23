package com.findjb.findjob.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FcmNotification {
    String fcmToken;
    String content;
}
