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

    @GetMapping("/_layout")
    public String layoutPage(){
        return "_layout";
    }

    @GetMapping("/index2")
    public String index2Page(){
        return "index2";
    }
}
