package com.example.try01.logger1;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyLogger {

    public void Task(){
        log.info("111");
        log.debug("222");
        log.error("333");
    }

}
