package com.ramitas.practica3app.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ramitas.practica3app.R;
import com.ramitas.practica3app.model.User;
import com.ramitas.practica3app.network.ApiClient;
import com.ramitas.practica3app.api.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddUser extends AppCompatActivity {
    private EditText usernameEditText, emailEditText, passwordEditText;
    private Spinner roleSpinner;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        usernameEditText = findViewById(R.id.editTextUsername); // Corregido
        emailEditText = findViewById(R.id.editTextEmail); // Corregido
        passwordEditText = findViewById(R.id.editTextPassword); // Corregido
        roleSpinner = findViewById(R.id.roleSpinner);
        saveButton = findViewById(R.id.buttonSave); // Corregido

        saveButton.setOnClickListener(v -> addUser());
    }

    private void addUser() {
        String username = usernameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String role = roleSpinner.getSelectedItem() != null ? roleSpinner.getSelectedItem().toString() : "";

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || role.isEmpty()) {
            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        User newUser = new User(username, email, password, role); // Usa el constructor de 4 parámetros
        ApiService apiService = ApiClient.getApiClient().create(ApiService.class);
        apiService.addUser(newUser).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(AddUser.this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                    finish(); // Regresa a AdminActivity
                } else {
                    Toast.makeText(AddUser.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(AddUser.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
