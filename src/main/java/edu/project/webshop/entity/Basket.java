package edu.project.webshop.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "basket")
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "basket_id")
    private int id;

    @OneToMany(targetEntity = Course.class, mappedBy = "basket")
    private List<Course> courses;


}
