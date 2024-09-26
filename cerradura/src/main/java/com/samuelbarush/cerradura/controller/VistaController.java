package com.samuelbarush.cerradura.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VistaController {

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/fibonacci")
    public String fibonacci(){
        return "fibonacci";
    }

    @GetMapping("/factorial")
    public String factorial(){
        return "factorial";
    }
    
}
