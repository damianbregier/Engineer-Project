package edu.project.webshop.controller;

import edu.project.webshop.entity.User;
import edu.project.webshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/profile")
    public ModelAndView profile(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("user", loggedUser);
        modelAndView.setViewName("profile");
        return modelAndView;
    }

    @PostMapping("/updateUserProfile")
    public String saveEmployee(@ModelAttribute("user") User user) {
        // save employee to database
        userService.saveOnlyUserDetails(user);
        return "redirect:/profile";
    }

}
