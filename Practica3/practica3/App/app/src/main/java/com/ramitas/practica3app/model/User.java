package com.ramitas.practica3app.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class User implements Serializable {
    private String id;
    private String username;
    private String email;
    private String password;

    @SerializedName("rol")
    private String role;

    @SerializedName("fotoPerfil")
    private String fotoPerfil;

    // Constructor completo
    public User(String id, String username, String password, String email, String role, String fotoPerfil) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.fotoPerfil = fotoPerfil;
    }

    // Constructor sin foto de perfil
    public User(String username, String email, String password, String role) {
        this(null, username, password, email, role, null); // id se establece como null
    }

    // Constructor con id, sin foto de perfil
    public User(String id, String username, String password, String email, String role) {
        this(id, username, password, email, role, null);
    }

    // Getters y Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }
    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
}
