package com.samuelbarush.cerradura.controller;
//Controla el endpoint de coneccion

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import com.samuelbarush.cerradura.model.CerraduraService;


@Controller
public class CerraduraController {

    @Autowired
    private CerraduraService cerraduraService;

    @PostMapping("/calculate-fibonacci")
    public String calculateFibonacci(@RequestParam("number") int number, Model model) {
        long result = cerraduraService.getFibonacci(number);
        model.addAttribute("result", result);
        model.addAttribute("number", number);
        return "fibonacci";
    }

    @PostMapping("/calculate-factorial")
    public String calculateFactorial(@RequestParam("number") int number, Model model) {
        long result = cerraduraService.getFactorial(number);
        model.addAttribute("result", result);
        model.addAttribute("number", number);  
        return "factorial";
    }
}
