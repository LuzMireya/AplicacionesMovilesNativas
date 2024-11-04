package com.example.practica3.service;

import com.example.practica3.model.User;
import com.example.practica3.repository.UserRepository; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository; 

    public User login(User user) {
        // Aquí iría la lógica para validar al usuario
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    public User register(User user) {
        // Aquí iría la lógica para registrar un nuevo usuario
        if (userRepository.findByUsername(user.getUsername()) == null) {
            return userRepository.save(user); 
        }
        return null; 
    }

    public List<User> getAllUsers() {
        return userRepository.findAll(); // Retorna todos los usuarios de la base de datos
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null); 
    }
    public User updateUser(Long id, User user) {
        if (userRepository.existsById(id)) {
            user.setId(id); // Asegúrate de que el ID del usuario a actualizar es correcto
            return userRepository.save(user); // Guarda el usuario actualizado
        }
        return null; // Si no existe, retorna null
    }
    
    public boolean deleteUserById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id); // Elimina el usuario usando el repositorio
            return true;
        } else {
            return false; // Si el usuario no existe, devuelve falso
        }
    }
    
    
}
