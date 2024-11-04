package com.ramitas.practica3app.view;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.ramitas.practica3app.R;
import com.ramitas.practica3app.model.User;
import com.ramitas.practica3app.network.ApiClient;
import com.ramitas.practica3app.repository.UserRepository;
import com.ramitas.practica3app.api.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateUser extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 100;
    private EditText usernameEditText, emailEditText, passwordEditText; // Agregar EditText para la contraseña
    private Button saveButton, selectImageButton;
    private ImageView profileImageView;
    private User currentUser;
    private UserRepository userRepository;
    private Uri imageUri; // Uri de la imagen seleccionada

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        userRepository = new UserRepository(ApiClient.getApiClient().create(ApiService.class));

        usernameEditText = findViewById(R.id.usernameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText); // Inicializar el EditText para la contraseña
        saveButton = findViewById(R.id.saveButton);
        selectImageButton = findViewById(R.id.selectImageButton);
        profileImageView = findViewById(R.id.profileImageView);

        currentUser = (User) getIntent().getSerializableExtra("USER");

        if (currentUser != null) {
            usernameEditText.setText(currentUser.getUsername());
            emailEditText.setText(currentUser.getEmail());
            // Cargar la imagen existente si hay una URI
            if (currentUser.getFotoPerfil() != null) {
                imageUri = Uri.parse(currentUser.getFotoPerfil());
                profileImageView.setImageURI(imageUri);
            }
        }

        selectImageButton.setOnClickListener(v -> {
            // Verificar y solicitar permisos de almacenamiento
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
            } else {
                openGallery(); // Abre la galería si ya tienes permiso
            }
        });

        saveButton.setOnClickListener(v -> {
            String updatedUsername = usernameEditText.getText().toString().trim();
            String updatedEmail = emailEditText.getText().toString().trim();
            String updatedPassword = passwordEditText.getText().toString().trim(); // Obtener la nueva contraseña

            if (updatedUsername.isEmpty() || updatedEmail.isEmpty()) {
                Toast.makeText(UpdateUser.this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            currentUser.setUsername(updatedUsername);
            currentUser.setEmail(updatedEmail);

            // Guardar la URI de la imagen
            if (imageUri != null) {
                currentUser.setFotoPerfil(imageUri.toString()); // Guardamos la URI en lugar de base64
                Log.d("UpdateUser", "Foto de perfil actualizada: " + currentUser.getFotoPerfil()); // Imprimir la URL de la imagen
            }

            // Si se ingresó una nueva contraseña, la guardamos
            if (!updatedPassword.isEmpty()) {
                currentUser.setPassword(updatedPassword); // Asegúrate de que tu modelo User tenga un método para establecer la contraseña
            }

            userRepository.updateUser(currentUser.getId(), currentUser).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        // Regresar a ProfileUserActivity y pasar el usuario actualizado
                        Intent intent = new Intent();
                        intent.putExtra("USER", currentUser);
                        setResult(RESULT_OK, intent);
                        finish();
                    } else {
                        Toast.makeText(UpdateUser.this, "Error al actualizar el usuario: " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(UpdateUser.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            profileImageView.setImageURI(imageUri);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery(); // Abre la galería si el permiso es concedido
            } else {
                Toast.makeText(this, "Permiso denegado para acceder a la galería", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
