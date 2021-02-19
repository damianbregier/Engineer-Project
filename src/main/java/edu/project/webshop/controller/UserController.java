package edu.project.webshop.controller;

import edu.project.webshop.entity.Cart;
import edu.project.webshop.entity.Role;
import edu.project.webshop.entity.User;
import edu.project.webshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<User> page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<User> listUsers = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listUsers", listUsers);
        return "usersView";
    }

    @GetMapping("/usersView")
    public String viewHomePage(Model model) {
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

        return findPaginated(1,"email", "asc", model);
    }


    @GetMapping(value="/showNewUserForm")
    public ModelAndView showNewUserForm(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("new_user");
        return modelAndView;
    }

    @PostMapping(value = "/showNewUserForm")
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
            modelAndView.setViewName("new_user");
        } else {
            user.setCart(cart);
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "Użytkownik został zarejestrowany poprawnie!");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("new_user");

        }
        return modelAndView;
    }



    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") int id, Model model) {

        // get employee from the service
        User user = userService.getUserById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("user", user);

        return "update_user";
    }

    @PostMapping("/updateUser")
    public String saveEmployee(@ModelAttribute("user") User user) {
        // save employee to database
        userService.saveOnlyUserDetails(user);
        return "redirect:/usersView";
    }


    @GetMapping("/deleteUser/{id}")
    public String deleteEmployee(@PathVariable (value = "id") int id) {

        // call delete employee method
        this.userService.deleteUserById(id);
        return "redirect:/usersView";
    }
}
