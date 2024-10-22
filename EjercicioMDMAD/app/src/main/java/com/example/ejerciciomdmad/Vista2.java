package com.example.ejerciciomdmad;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ejerciciomdmad.R;

public class Vista2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView texto2 = (TextView) findViewById(R.id.main);
        texto2.setText("Nuevo texto para mostrar");
    }
}
