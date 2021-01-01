package edu.project.webshop.controller;

import edu.project.webshop.entity.User;
import edu.project.webshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        modelAndView.addObject("user", user);
        modelAndView.setViewName("sign-up");
        return modelAndView;
    }

    @PostMapping(value = "/register")
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "Użytkownik z tym adresem email już istnieje!");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("sign-up");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "Użytkownik został zarejestrowany poprawnie!");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("sign-up");

        }
        return modelAndView;
    }


}
