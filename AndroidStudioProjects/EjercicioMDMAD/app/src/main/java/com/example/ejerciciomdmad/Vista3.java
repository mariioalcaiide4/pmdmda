package com.example.ejerciciomdmad;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Vista3 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView texto3 = (TextView) findViewById(R.id.main);
        texto3.setText("Nuevo texto para mostrar");
    }
}
