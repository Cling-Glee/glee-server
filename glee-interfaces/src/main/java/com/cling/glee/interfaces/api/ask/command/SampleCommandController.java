package com.cling.glee.interfaces.api.ask.command;

import com.cling.glee.interfaces.api.ask.command.dto.SampleCommandDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sample")
public class SampleCommandController {

    @PostMapping("/register")
    public void sample(@RequestBody SampleCommandDTO sampleCommandDTO){

    }

}
