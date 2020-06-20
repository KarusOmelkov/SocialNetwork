package com.uspu.Cupcake.Repository;

import com.uspu.Cupcake.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findByFirstNameOrLastName(String firstName, String lastName);
    List<User> findByFirstNameAndLastName(String firstName, String lastName);
}
