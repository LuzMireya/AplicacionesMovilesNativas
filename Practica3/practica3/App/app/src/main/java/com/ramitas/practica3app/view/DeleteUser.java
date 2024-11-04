package com.ramitas.practica3app.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ramitas.practica3app.R;
import com.ramitas.practica3app.model.User;
import com.ramitas.practica3app.network.ApiClient;
import com.ramitas.practica3app.api.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteUser extends AppCompatActivity {
    private LinearLayout usersLayout;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        usersLayout = findViewById(R.id.usersLayout);
        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(v -> finish());

        loadUserRecords();
    }

    private void loadUserRecords() {
        ApiService apiService = ApiClient.getApiClient().create(ApiService.class);
        apiService.getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    for (User user : response.body()) {
                        addUserView(user);
                    }
                } else {
                    Toast.makeText(DeleteUser.this, "Error al cargar registros", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(DeleteUser.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addUserView(User user) {
        View userView = getLayoutInflater().inflate(R.layout.item_user_delete, null);
        TextView usernameTextView = userView.findViewById(R.id.userNameTextView);
        Button deleteButton = userView.findViewById(R.id.deleteButton);

        usernameTextView.setText(user.getUsername());

        deleteButton.setOnClickListener(v -> deleteUser(String.valueOf(user.getId()))); // Convertir el ID a String

        usersLayout.addView(userView);
    }

    private void deleteUser(String userId) {
        ApiService apiService = ApiClient.getApiClient().create(ApiService.class);
        apiService.deleteUser(userId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(DeleteUser.this, "Usuario eliminado", Toast.LENGTH_SHORT).show();
                    finish(); // Regresar a AdminActivity
                } else {
                    Toast.makeText(DeleteUser.this, "Error al eliminar usuario", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(DeleteUser.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
