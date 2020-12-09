package edu.project.webshop.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Data //getter, setter, RequiredArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="role")
public class Role {
    public String getRole;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String role;

    @OneToOne(mappedBy = "role")
    private User user;


}
