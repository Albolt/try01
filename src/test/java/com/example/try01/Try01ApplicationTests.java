package com.example.try01;

import com.example.try01.logger1.MyLogger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Try01ApplicationTests {

    @Autowired
    private MyLogger myLogger;

    @Test
    public void contextLoads() {
        myLogger.Task();
    }

}
