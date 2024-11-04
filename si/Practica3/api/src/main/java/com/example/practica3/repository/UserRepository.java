package com.example.practica3.repository;

import com.example.practica3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username); // Método para encontrar usuario por nombre de usuario
    User findByUsernameAndPassword(String username, String password); // Método para validar login
}
