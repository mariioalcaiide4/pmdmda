package com.example.pelculas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private EditText usernameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2); // Asegúrate de que activity_main2.xml existe y es válido.

        dbHelper = new DatabaseHelper(this); //Arranca la DB

        // Configuración Edge-to-Edge
        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return WindowInsetsCompat.CONSUMED;
            });
        }

        EditText usernameField = findViewById(R.id.textUsuario);
        EditText passwordField = findViewById(R.id.textContraseña);
        Button loginButton = findViewById(R.id.buttonSubmit);

        ImageView imageView = findViewById(R.id.fondo);
        Glide.with(this)
                .asGif()
                .load(R.drawable.fytq)
                .into(imageView);

        TextView registerTextView = findViewById(R.id.registerTextView); // Asegúrate de que este ID existe en tu XML
        registerTextView.setOnClickListener(v -> mostrarDialogoRegistro());

        // Rellenar automáticamente el último usuario guardado
        String ultimoUsuario = obtenerUltimoNombreUsuario();
        if (!ultimoUsuario.isEmpty()) {
            usernameField.setText(ultimoUsuario);
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameField.getText().toString();
                String password = passwordField.getText().toString();

                if (dbHelper.validarUsuario(username, password)) { // Usar la base de datos para validar
                    // Guardar el último usuario en SharedPreferences
                    guardarNombreUsuario(username);

                    Toast.makeText(MainActivity.this, "¡Bienvenido!", Toast.LENGTH_SHORT).show();
                    // Navegar a la actividad principal
                    Intent intent = new Intent(MainActivity.this, MainActivity4.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void mostrarDialogoRegistro() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Registrar nuevo usuario");

        // Layout personalizado para el diálogo
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_registro, null);
        builder.setView(dialogView);

        EditText usernameField = dialogView.findViewById(R.id.dialogUsername);
        EditText passwordField = dialogView.findViewById(R.id.dialogPassword);
        Button registerButton = dialogView.findViewById(R.id.dialogRegisterButton);

        AlertDialog dialog = builder.create();

        registerButton.setOnClickListener(v -> {
            String username = usernameField.getText().toString();
            String password = passwordField.getText().toString();

            if (!username.isEmpty() && !password.isEmpty()) {
                if (dbHelper.insertarUsuario(username, password)) {
                    Toast.makeText(MainActivity.this, "Usuario registrado con éxito.", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(MainActivity.this, "El usuario ya existe.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }


    // Guardar el nombre de usuario en SharedPreferences
    private void guardarNombreUsuario(String nombreUsuario) {
        SharedPreferences preferences = getSharedPreferences("misPreferencias", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("ultimoUsuario", nombreUsuario);
        editor.apply();
    }

    // Obtener el último nombre de usuario guardado
    private String obtenerUltimoNombreUsuario() {
        SharedPreferences preferences = getSharedPreferences("misPreferencias", MODE_PRIVATE);
        return preferences.getString("ultimoUsuario", ""); // Retorna "" si no hay un usuario guardado
    }
}
