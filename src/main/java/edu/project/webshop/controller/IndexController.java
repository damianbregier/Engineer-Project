package edu.project.webshop.controller;

import edu.project.webshop.entity.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @GetMapping(path = "/")
    public ModelAndView index(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return modelAndView;
        }else{
            modelAndView.setViewName("redirect:/shop");
            return modelAndView;
        }

    }

}
