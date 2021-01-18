package edu.project.webshop.service;

import edu.project.webshop.entity.Course;
import edu.project.webshop.entity.Cart;
import edu.project.webshop.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    public Cart saveCart(Cart cart){
        return cartRepository.save(cart);
    }

    public Cart findCartByid(int id){
        return cartRepository.findById(id);
    }
}
