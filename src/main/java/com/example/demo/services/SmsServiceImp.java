package com.example.demo.services;

import com.example.demo.models.Notification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.demo.constants.RabbitMqQuees.CUSTOMER_REGISTRATION;
import static com.example.demo.constants.Variables.COUNTRY_CODE;

@Service
public class SmsServiceImp implements ISmsService {
    @Autowired
    Environment environment;
    Logger LOG = LogManager.getLogger(SmsServiceImp.class);
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Override
    public void sendSms(Notification notification) {

        try {
            URL smslink = new
                    URL(environment.getRequiredProperty("smsgateway.host")+"compose?"+
                    "username="+environment.getRequiredProperty("smsgateway.username")+
                    "&api_key="+environment.getRequiredProperty("smsgateway.apikey")+
                    "&sender="+environment.getRequiredProperty("smsgateway.senderid")+
                    "&to="+COUNTRY_CODE+notification.getPhoneNumber()+
                    "&message="+java.net.URLEncoder.encode(notification.getMessage(), "UTF-8")+
                    "&msgtype=5&dlr=0");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            smslink.openStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);

            in.close();
            LOG.info("SMS SENT TO "+ notification.getPhoneNumber());
        } catch (IOException e) {
            LOG.error("ERROR OCCURED WHEN TRYING TO SEND THE EMAIL "+e.getMessage());
        }

    }
}
