package com.ramitas.practica3app.view;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ramitas.practica3app.R;

public class AdminActivity extends AppCompatActivity {
    private Button viewRecordsButton, addUserButton, deleteUserButton, logoutButton; // Botón de cerrar sesión agregado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        viewRecordsButton = findViewById(R.id.viewRecordsButton);
        addUserButton = findViewById(R.id.addUserButton);
        deleteUserButton = findViewById(R.id.deleteUserButton);
        logoutButton = findViewById(R.id.logoutButton); // Inicializar el botón de cerrar sesión

        viewRecordsButton.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, ViewRecords.class);
            startActivity(intent);
        });

        addUserButton.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, AddUser.class);
            startActivity(intent);
        });

        deleteUserButton.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, DeleteUser.class);
            startActivity(intent);
        });

        logoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}


