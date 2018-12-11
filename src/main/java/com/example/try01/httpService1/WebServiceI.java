package com.example.try01.httpService1;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface WebServiceI {

    //使用@WebMethod注解WebServiceI中的方法
    @WebMethod
    String sayHello(String name);

    @WebMethod
    String save(String name, String pwd);
}
