package com.example.studio;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void pulsado1 (View view){
        Toast.makeText(getApplicationContext(), "Has hecho clic en el botón 1", Toast.LENGTH_SHORT).show();
        Log.i("EJEMPLO", "Boton pulsado");
        Intent activity = new Intent(this, MainActivity2.class);
        startActivity(activity);
    }

    public void pulsado2 (View view) {
        Toast.makeText(getApplicationContext(), "Has hecho clic en el botón 2", Toast.LENGTH_SHORT).show();
        Log.i("EJEMPLO", "Boton pulsado");
        Intent activity = new Intent(this, MainActivity3.class);
        startActivity(activity);
    }

    public void pulsado3 (View view) {
        Toast.makeText(getApplicationContext(), "Has hecho clic en el botón 3", Toast.LENGTH_SHORT).show();
    }

    public void pulsado4 (View view) {
        Toast.makeText(getApplicationContext(), "Has hecho clic en el botón 4", Toast.LENGTH_SHORT).show();

    }


}