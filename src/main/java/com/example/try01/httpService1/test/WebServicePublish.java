package com.example.try01.httpService1.test;


import com.example.try01.httpService1.WebServiceImpl;

import javax.xml.ws.Endpoint;

public class WebServicePublish {


    public static void main(String[] args){
        String address = "http://localhost:8980/WS_Server/Webservice";

        Endpoint.publish(address, new WebServiceImpl());
        System.out.println("发布成功");
    }
}
