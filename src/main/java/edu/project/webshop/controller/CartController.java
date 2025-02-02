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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    /*
    To make it work properly post mapping should take care of assigning things
    and then redirect to "/cart" which is get mapping name
    while get mapping takes care of showing list of items

    using post mapping to show list of items always doesn't show latest item
    while refreashing page adds more items to cart!
     */

    @GetMapping("/cart")
    public String cart(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = userService.findUserByEmail(auth.getName());

        int curentId = loggedUser.getId();
        Cart cart = cartService.findCartByid(loggedUser.getId());
        List<CartItem> cartItemList = cartItemService.findAllCartItems();

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

        model.addAttribute("cartItemList", cartItemList);
        model.addAttribute("currentId", curentId);
        return "cart";
    }



    @PostMapping("/cart")
    public String addToCart(@RequestParam int id, Model model){
        Course course = courseService.getCourseById(id);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = userService.findUserByEmail(auth.getName());

        int curentId = loggedUser.getId();

        Cart cart = cartService.findCartByid(loggedUser.getId());
        CartItem cartItem = new CartItem();
        cartItem.setCourse(course);
        cartItem.setCart(cart);

        cartItemService.saveCartItem(cartItem);


        return "redirect:/cart";
    }

    @GetMapping("/removeFromCart")
    public String removeFromCart(@RequestParam int id, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = userService.findUserByEmail(auth.getName());

        int curentId = loggedUser.getId();
        Cart cart = cartService.findCartByid(loggedUser.getId());
        CartItem currentItem = cartItemService.findCartItemByid(id);

        cartItemService.deleteCartItem(currentItem);
        return "redirect:/cart";

    }


}
