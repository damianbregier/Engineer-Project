package edu.project.webshop.controller;

import edu.project.webshop.entity.Role;
import edu.project.webshop.entity.User;
import edu.project.webshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;

@Controller
public class ShopController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/shop")
    public String shop(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = userService.findUserByEmail(auth.getName());
        HashSet<Role> loggedUserRoles = new HashSet<>(loggedUser.getRoles());
        String log_user_role = "";
        for(Role role : loggedUserRoles){
            if(role.getRole().equals("ADMIN")){
                log_user_role = String.valueOf(role.getRole());
            }else if(role.getRole().equals("USER")){
                log_user_role = String.valueOf(role.getRole());
            }
        }
        model.addAttribute("log_user_role", log_user_role);

        return "/shop";
    }

}