package com.example.demo.listeners;

import com.example.demo.models.Notification;
import com.example.demo.services.ISmsService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import static com.example.demo.constants.RabbitMqQuees.CUSTOMER_REGISTRATION;

@Configuration
public class NotificationListener {

        @Autowired
        ISmsService smsService;
       @RabbitListener(queues = CUSTOMER_REGISTRATION)
       public void sendRegistrationNotification(Notification notification){
         if (notification.getType().equals("SMS")){
             smsService.sendSms(notification);
         }
       }

}
