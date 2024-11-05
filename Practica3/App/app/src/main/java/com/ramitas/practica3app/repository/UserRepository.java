package com.ramitas.practica3app.repository;

import com.ramitas.practica3app.model.User;
import com.ramitas.practica3app.api.ApiService;
import java.util.List;
import retrofit2.Call;

public class UserRepository {
    private ApiService apiService;

    // Constructor
    public UserRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public Call<User> loginUser(User user) {
        return apiService.loginUser(user);
    }

    public Call<User> registerUser(User user) {
        return apiService.registerUser(user);
    }

    public Call<List<User>> getUsers() {
        return apiService.getUsers();
    }

    // Método para actualizar el usuario
    public Call<User> updateUser(String userId, User user) {
        return apiService.updateUser(userId, user); // Asegúrate de que este método esté definido en ApiService
    }
}
