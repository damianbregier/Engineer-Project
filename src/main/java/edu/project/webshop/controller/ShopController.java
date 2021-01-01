package edu.project.webshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShopController {

    @GetMapping(value = "/shop")
    public ModelAndView shop(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("shop");
        return modelAndView;
    }
}