package com.ramitas.practica3app.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;  // Importa Button
import android.widget.EditText;
import android.widget.TextView; // Importa TextView
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ramitas.practica3app.R;
import com.ramitas.practica3app.model.User;
import com.ramitas.practica3app.network.ApiClient;
import com.ramitas.practica3app.repository.UserRepository;
import com.ramitas.practica3app.api.ApiService;
import com.ramitas.practica3app.view.ProfileUserActivity;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameEditText, passwordEditText;
    private Button loginButton;
    private TextView registerTextView; // Para el TextView de registro
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        registerTextView = findViewById(R.id.registerTextView);

        // Inicializa el repositorio
        userRepository = new UserRepository(ApiClient.getApiClient().create(ApiService.class));

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Validar entradas
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Por favor ingresa usuario y contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Crear usuario
                User user = new User(null, username, password, null, "user");

                // Iniciar sesión
                loginUser(user);
            }
        });

        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para ir a la actividad de registro
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loginUser(User user) {
        Call<User> call = userRepository.loginUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(LoginActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

                    // Redirigir al usuario a la pantalla de perfil
                    if (response.body().getRole().equals("admin")) {
                        // Redirigir a la actividad de administrador
                        Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                        intent.putExtra("USER", response.body()); // Asegúrate de que estás pasando el objeto User
                        startActivity(intent);
                        finish();
                    } else {
                        // Redirigir a la actividad de usuario

                        Intent intent = new Intent(LoginActivity.this, ProfileUserActivity.class);
                        intent.putExtra("USER", response.body()); // Asegúrate de que estás pasando el objeto User
                        startActivity(intent);
                        finish(); // Opcional: Finaliza la actividad de login para que no se pueda volver
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Error de inicio de sesión", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
