package com.example.demo.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ApiController {

    private static final Logger log = LoggerFactory.getLogger(ApiController.class);

    // curl http://localhost:8080/api/hello
    @RequestMapping("hello")
    private String hello() {
        log.info("SpringBoot!");
        return "SpringBoot!";
    }

}
