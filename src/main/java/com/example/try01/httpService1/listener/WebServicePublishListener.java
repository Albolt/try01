package com.example.try01.httpService1.listener;

import com.example.try01.httpService1.WebServiceImpl;

import javax.servlet.Servlet;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.xml.ws.Endpoint;

@WebListener
public class WebServicePublishListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        String address = "http://localhost:8089/WS_Server/Webservice";
        Endpoint.publish(address, new WebServiceImpl());
        System.out.println("listener发布成功");
    }
}
