package edu.project.webshop.repository;

import edu.project.webshop.entity.Cart;
import edu.project.webshop.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository <CartItem, Integer> {
    public CartItem findById (int id);

}
