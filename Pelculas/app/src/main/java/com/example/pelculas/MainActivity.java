package com.example.pelculas;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.bumptech.glide.Glide;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private final HashMap<String, String> validUsers = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2); // Asegúrate de que activity_main3.xml existe y es válido.

        // Configuración Edge-to-Edge
        View mainView = findViewById(R.id.main); // Asegúrate de que el ID "main" existe en activity_main3.xml
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return WindowInsetsCompat.CONSUMED; // Consumir insets si no deseas que continúe propagándose.
            });
        }

        validUsers.put("mariioalcaide4", "1234");
        validUsers.put("teresita", "teresita");
        validUsers.put("prueba", "prueba");

        EditText usernameField = findViewById(R.id.textUsuario);
        EditText passwordField = findViewById(R.id.textContraseña);
        Button loginButton = findViewById(R.id.buttonSubmit);



        ImageView imageView = findViewById(R.id.fondo);
        Glide.with(this)
                .asGif()
                .load(R.drawable.fytq)
                .into(imageView);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameField.getText().toString();
                String password = passwordField.getText().toString();

                if (isValidUser(username, password)) {

                    Toast.makeText(MainActivity.this, "¡Bienvenido!", Toast.LENGTH_SHORT).show();
                    // Vamos a la actividad de la lista de la ropa
                    Intent intent = new Intent(MainActivity.this, MainActivity4.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Validación de usuarios
    private boolean isValidUser(String username, String password) {
        return validUsers.containsKey(username) && validUsers.get(username).equals(password);
    }

}