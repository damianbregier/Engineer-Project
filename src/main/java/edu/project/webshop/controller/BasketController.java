package edu.project.webshop.controller;

import edu.project.webshop.entity.Course;
import edu.project.webshop.entity.Role;
import edu.project.webshop.entity.Tag;
import edu.project.webshop.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashSet;
import java.util.List;

@Controller
public class BasketController {

    @GetMapping(value = "/basket")
    public String shop(Model model){

        return "/basket";
    }
}
