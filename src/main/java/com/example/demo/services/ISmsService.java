package com.example.demo.services;

import com.example.demo.models.Notification;

public interface ISmsService {

    public void sendSms(Notification notification);
}
