package com.zechariah.fleetms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {

    @GetMapping("/index")
    public String indexPage(){
        return "index";
    }

    @GetMapping("/widgets")
    public String widgetsPage(){
        return "widgets";
    }
}
