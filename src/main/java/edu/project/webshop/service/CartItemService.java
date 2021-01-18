package edu.project.webshop.service;

import edu.project.webshop.entity.Cart;
import edu.project.webshop.entity.CartItem;
import edu.project.webshop.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {

    @Autowired
    CartItemRepository cartItemRepository;

    public CartItem saveCartItem(CartItem cartItem){
        return cartItemRepository.save(cartItem);
    }

    public CartItem findCartItemByid(int id){
        return cartItemRepository.findById(id);
    }

    public List <CartItem> findAllCartItems(){
        return cartItemRepository.findAll();
    }

}
