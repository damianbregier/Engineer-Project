package edu.project.webshop.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data //getter, setter, RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @OneToOne(mappedBy = "role")
    private User user;


}
