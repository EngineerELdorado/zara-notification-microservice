package com.example.demo.models;

import lombok.Data;


@Data
public class Notification {
    private Long id;
    private String type;
    private String subject;
    private String message;
    private String phoneNumber;
    private String email;
    private String status;
}
