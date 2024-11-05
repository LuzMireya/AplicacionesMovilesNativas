package com.example.practica3.controller;

import com.example.practica3.model.User;
import com.example.practica3.service.UserService; // Asegúrate de que tienes un servicio para manejar la lógica
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService; // Inyecta tu servicio

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) {
        // Lógica para validar el usuario
        User loggedInUser = userService.login(user); // Implementa la lógica en tu UserService
        if (loggedInUser != null) {
            return ResponseEntity.ok(loggedInUser); // Retorna el usuario si es exitoso
        } else {
            return ResponseEntity.status(401).build(); // Credenciales incorrectas
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        // Lógica para registrar un nuevo usuario
        User registeredUser = userService.register(user); // Implementa la lógica en tu UserService
        if (registeredUser != null) {
            return ResponseEntity.ok(registeredUser);
        } else {
            return ResponseEntity.status(400).build(); // Error en el registro
        }
    }

    // Método para obtener usuarios (opcional)
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    // Agregar método en UserController
@GetMapping("/{id}")
public ResponseEntity<User> getUserById(@PathVariable Long id) {
    User user = userService.getUserById(id); // Suponiendo que tienes este método en UserService
    if (user != null) {
        return ResponseEntity.ok(user); // Retorna el usuario si se encuentra
    } else {
        return ResponseEntity.notFound().build(); // Retorna 404 si el usuario no existe
    }
}

@PutMapping("/{id}")
public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
    User updatedUser = userService.updateUser(id, user);
    if (updatedUser != null) {
        return ResponseEntity.ok(updatedUser); // Retorna el usuario actualizado
    } else {
        return ResponseEntity.notFound().build(); // Retorna 404 si no se encuentra el usuario
    }
}

@DeleteMapping("/{id}")
public ResponseEntity<String> deleteUser(@PathVariable Long id) {
    boolean isDeleted = userService.deleteUserById(id); // Llamamos a un método en UserService para eliminar el usuario
    if (isDeleted) {
        return ResponseEntity.ok("Usuario eliminado exitosamente");
    } else {
        return ResponseEntity.status(404).body("Usuario no encontrado");
    }
}

@PutMapping("/add") 
public ResponseEntity<User> addUser(@RequestBody User user) {
    User registeredUser = userService.register(user);
    if (registeredUser != null) {
        return ResponseEntity.ok(registeredUser);
    } else {
        return ResponseEntity.status(400).build();
    }
}


}
