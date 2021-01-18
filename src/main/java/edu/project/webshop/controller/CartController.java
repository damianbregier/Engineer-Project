package edu.project.webshop.controller;

import edu.project.webshop.entity.*;
import edu.project.webshop.service.CartItemService;
import edu.project.webshop.service.CartService;
import edu.project.webshop.service.CourseService;
import edu.project.webshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashSet;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    UserService userService;

    @Autowired
    CourseService courseService;

    @Autowired
    CartService cartService;

    @Autowired
    CartItemService cartItemService;

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable(value = "id") int id, Model model){
        Course course = courseService.getCourseById(id);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = userService.findUserByEmail(auth.getName());

        int curentId = loggedUser.getId();

        Cart cart = cartService.findCartByid(loggedUser.getId());

        List<CartItem> cartItemList = cartItemService.findAllCartItems();


        model.addAttribute("cartItemList", cartItemList);
        model.addAttribute("currentId", curentId);

        CartItem cartItem = new CartItem();
        cartItem.setCourse(course);
        cartItem.setCart(cart);

        cartItemService.saveCartItem(cartItem);


        return "basket";
    }
}
