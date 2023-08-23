package com.cling.glee.interfaces.api.command;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class SampleCommandController {


    @Value("${spring.jpa.open-in-view}")
    private String openInView;


    @GetMapping("/hello")
    public String hello() {
        return "hello from " + " openInView: " + openInView;
    }

    @GetMapping("/login/oauth2/code/kakao")
    public String checkCode() {
        return "code";
    }


}