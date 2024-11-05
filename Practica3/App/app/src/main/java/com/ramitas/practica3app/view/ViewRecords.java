package com.ramitas.practica3app.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;  // Asegúrate de incluir esta línea
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.ramitas.practica3app.R;
import com.ramitas.practica3app.api.ApiService;
import com.ramitas.practica3app.model.User;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewRecords extends AppCompatActivity {

    private LinearLayout recordsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_records);

        recordsLayout = findViewById(R.id.recordsLayout);
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        // Inicializar Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/api/")  // Asegúrate de reemplazar con la URL base correcta
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        fetchUsers(apiService);
    }

    private void fetchUsers(ApiService apiService) {
        Call<List<User>> call = apiService.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<User> userList = response.body();
                    Log.d("ViewRecords", "Número de usuarios: " + userList.size());
                    for (User user : userList) {
                        addUserView(user);
                    }
                } else {
                    Log.e("ViewRecords", "Error en la respuesta de la API");
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("ViewRecords", "Fallo al obtener usuarios: " + t.getMessage());
            }
        });
    }

    private void addUserView(User user) {
        View userItemView = getLayoutInflater().inflate(R.layout.item_user_view, recordsLayout, false);

        TextView userIdTextView = userItemView.findViewById(R.id.userIdTextView);
        TextView userNameTextView = userItemView.findViewById(R.id.userNameTextView);
        TextView userEmailTextView = userItemView.findViewById(R.id.userEmailTextView);
        TextView userRoleTextView = userItemView.findViewById(R.id.userRoleTextView);
        ImageView userProfileImageView = userItemView.findViewById(R.id.userProfileImageView);

        userIdTextView.setText("ID: " + user.getId());
        userNameTextView.setText("Username: " + user.getUsername());
        userEmailTextView.setText("Email: " + user.getEmail());
        userRoleTextView.setText("Role: " + user.getRole());

        // Cargar imagen o imagen predeterminada si es null
        String profileImageUrl = user.getFotoPerfil();
        if (profileImageUrl == null || profileImageUrl.isEmpty()) {
            profileImageUrl = "android.resource://" + getPackageName() + "/drawable/ic_profile";
        }

        Glide.with(this)
                .load(profileImageUrl)
                .placeholder(R.drawable.ic_profile)  // Imagen predeterminada en caso de error
                .into(userProfileImageView);

        recordsLayout.addView(userItemView);
    }
}
