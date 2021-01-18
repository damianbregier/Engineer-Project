package edu.project.webshop.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "carts")
public class Shopping_cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private int id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


}
