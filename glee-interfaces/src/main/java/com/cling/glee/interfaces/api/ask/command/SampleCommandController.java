package com.cling.glee.interfaces.api.ask.command;

import com.cling.glee.interfaces.api.ask.command.dto.SampleCommandDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/api/sample")
public class SampleCommandController {


    @Value("${spring.jpa.open-in-view}")
    private String openInView;

    @PostMapping("/register")
    public void sample(@RequestBody SampleCommandDTO sampleCommandDTO){

    }

    @GetMapping("/hello")
    public String hello() {
        return "hello from " + " openInView: " + openInView;
    }

}