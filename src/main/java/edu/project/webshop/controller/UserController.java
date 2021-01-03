package edu.project.webshop.controller;

import edu.project.webshop.entity.User;
import edu.project.webshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return findPaginated(1,"email", "asc", model);
    }

    @GetMapping("/showNewUserForm")
    public String showNewEmployeeForm(Model model) {
        // create model attribute to bind form data
        User user = new User();
        model.addAttribute("user", user);
        return "new_user";
    }

    @PostMapping("/saveUser")
    public String saveEmployee(@ModelAttribute("user") User user) {
        // save employee to database
        userService.saveUser(user);
        return "redirect:/usersView";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") int id, Model model) {

        // get employee from the service
        User user = userService.getUserById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("user", user);

        return "update_user";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteEmployee(@PathVariable (value = "id") int id) {

        // call delete employee method
        this.userService.deleteUserById(id);
        return "redirect:/usersView";
    }
}
