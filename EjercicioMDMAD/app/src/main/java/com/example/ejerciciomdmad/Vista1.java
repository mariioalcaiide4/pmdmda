package com.example.ejerciciomdmad;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Vista1 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView texto1 = (TextView) findViewById(R.id.main);
        texto1.setText("Nuevo texto para mostrar");
    }
}
