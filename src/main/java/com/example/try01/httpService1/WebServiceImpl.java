package com.example.try01.httpService1;


import com.example.try01.httpService1.WebServiceI;

import javax.jws.WebService;

@WebService(endpointInterface = "com.example.try01.httpService1.WebServiceImpl")
public class WebServiceImpl implements WebServiceI {

    @Override
    public String sayHello(String name) {
        System.out.println("WebService sayHello" + name);
        return "sayHello " + name;
    }

    @Override
    public String save(String name, String pwd) {
        System.out.println("WebService save " + name + ", " + pwd);
        return "save success";
    }
}
