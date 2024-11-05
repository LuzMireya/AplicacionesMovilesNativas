package com.ramitas.practica3app.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ramitas.practica3app.R;
import com.ramitas.practica3app.model.User;
import com.ramitas.practica3app.network.ApiClient;
import com.ramitas.practica3app.repository.UserRepository;
import com.ramitas.practica3app.api.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserListActivity extends AppCompatActivity {
    private ListView userListView;
    private Button addUserButton;
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        userListView = findViewById(R.id.userListView);
        addUserButton = findViewById(R.id.addUserButton);

        userRepository = new UserRepository(ApiClient.getApiClient().create(ApiService.class));

        addUserButton.setOnClickListener(v -> {
            Intent intent = new Intent(UserListActivity.this, AddUser.class);
            startActivity(intent);
        });

        loadUsers();
    }

    private void loadUsers() {
        Call<List<User>> call = userRepository.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    List<User> users = response.body();
                    // Aquí puedes configurar tu ListView con los usuarios
                } else {
                    Toast.makeText(UserListActivity.this, "Error al cargar usuarios", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(UserListActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
