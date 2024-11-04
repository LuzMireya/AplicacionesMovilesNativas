package com.ramitas.practica3app.api;

import com.ramitas.practica3app.model.User;
import java.util.List; // Importa la clase List
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT; // Importa el método PUT
import retrofit2.http.Path;
import retrofit2.http.DELETE;

public interface ApiService {
    @POST("users/login")
    Call<User> loginUser(@Body User user);

    @POST("users/register")
    Call<User> registerUser(@Body User user);

    @PUT("users/add") // Cambia @POST a @PUT
    Call<User> addUser(@Body User user);

    @GET("users") // Asegúrate de que esta URL sea correcta
    Call<List<User>> getUsers(); // Agrega el método para obtener usuarios

    // Agrega el método para actualizar el usuario
    @PUT("users/{id}") // Cambiado a PUT
    Call<User> updateUser(@Path("id") String userId, @Body User user); // Cambiado a String
    // ApiService.java
    @DELETE("users/{id}")
    Call<Void> deleteUser(@Path("id") String userId); // Método para eliminar un usuario

}
