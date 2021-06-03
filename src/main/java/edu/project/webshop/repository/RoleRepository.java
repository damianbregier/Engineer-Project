package edu.project.webshop.repository;

import edu.project.webshop.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository <Role, Integer> {
    Role findByRole(String role);
}
