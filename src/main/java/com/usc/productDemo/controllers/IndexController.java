package com.usc.productDemo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @GetMapping("")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("index.html");
        return mav;
    }
}