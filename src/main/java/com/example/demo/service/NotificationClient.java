package com.example.demo.service;

public class NotificationClient {

    public String api_key;
    public int timeOut;

    public NotificationClient(String api_key, int timeOut) {
        this.api_key = api_key;
        this.timeOut = timeOut;
    }

    public void notify(String msg){
        System.out.println("Notification for " +api_key+":"+ msg);
    }
}
