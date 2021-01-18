package edu.project.webshop.controller;

import edu.project.webshop.entity.Cart;
import edu.project.webshop.entity.User;
import edu.project.webshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class SignUpController {

    @Autowired
    private UserService userService;



    @GetMapping(value="/register")
    public ModelAndView register(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("sign-up");

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return modelAndView;
        }else{
            modelAndView.setViewName("redirect:/shop");
            return modelAndView;
        }
    }

    @PostMapping(value = "/register")
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Cart cart = new Cart();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "Użytkownik z tym adresem email już istnieje!");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("sign-up");
        } else {
            user.setCart(cart);
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "Użytkownik został zarejestrowany poprawnie!");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("sign-up");

        }
        return modelAndView;
    }


}
