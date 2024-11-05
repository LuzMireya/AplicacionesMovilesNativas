package com.ramitas.practica3app.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.ramitas.practica3app.R;
import com.ramitas.practica3app.model.User;

public class ProfileUserActivity extends AppCompatActivity {
    private TextView usernameTextView, emailTextView;
    private Button updateButton, logoutButton; // Botón de cerrar sesión agregado
    private ImageView profileImageView;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);

        usernameTextView = findViewById(R.id.usernameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        updateButton = findViewById(R.id.updateButton);
        logoutButton = findViewById(R.id.logoutButton); // Inicializar el botón de cerrar sesión
        profileImageView = findViewById(R.id.profileImageView);

        currentUser = (User) getIntent().getSerializableExtra("USER");

        if (currentUser != null) {
            usernameTextView.setText(currentUser.getUsername());
            emailTextView.setText(currentUser.getEmail());

            Log.d("ProfileUserActivity", "Foto de perfil URL: " + currentUser.getFotoPerfil());

            if (currentUser.getFotoPerfil() != null && !currentUser.getFotoPerfil().isEmpty()) {
                Uri imageUri = Uri.parse(currentUser.getFotoPerfil());
                Glide.with(this)
                        .load(imageUri)
                        .placeholder(R.drawable.ic_profile)
                        .error(R.drawable.ic_profile)
                        .into(profileImageView);
            } else {
                profileImageView.setImageResource(R.drawable.ic_profile);
            }
        }

        updateButton.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileUserActivity.this, UpdateUser.class);
            intent.putExtra("USER", currentUser);
            startActivityForResult(intent, 1);
        });

        logoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileUserActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null) {
                currentUser = (User) data.getSerializableExtra("USER");
                usernameTextView.setText(currentUser.getUsername());
                emailTextView.setText(currentUser.getEmail());

                if (currentUser.getFotoPerfil() != null && !currentUser.getFotoPerfil().isEmpty()) {
                    Uri imageUri = Uri.parse(currentUser.getFotoPerfil());
                    Glide.with(this)
                            .load(imageUri)
                            .placeholder(R.drawable.ic_profile)
                            .error(R.drawable.ic_profile)
                            .into(profileImageView);
                } else {
                    profileImageView.setImageResource(R.drawable.ic_profile);
                }
            }
        }
    }
}
