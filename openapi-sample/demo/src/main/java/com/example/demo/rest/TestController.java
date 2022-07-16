package com.example.demo.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.exception.MyCustomException;

@RestController
public class TestController {
    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value = "/test/{id}")
    private String hello(@PathVariable String id) throws MyCustomException {
        log.info("Hello World!!");
        if ("0000".equals(id)) {
            throw new MyCustomException(); // 想定される Exception
        }
        return "Hello world!!";
    }

    @RequestMapping(value = "/test-null")
    private String hello() {
        log.info("Hello World!!");

        String s = null;
        s.equals("hoge"); // うっかり NPE 発生

        return "Hello world!!";
    }
}
