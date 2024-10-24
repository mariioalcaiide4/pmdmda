package com.example.studio;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity4 extends AppCompatActivity {

    ImageView imagensota;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main4);
        imagensota = (ImageView) findViewById(R.id.imagen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void rotar1(View view1) {
        Log.i("EJEMPLO", "Boton pulsado");
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.rotar1);
        animation.setRepeatMode(Animation.RESTART);
        animation.setRepeatCount(1);
        imagensota.startAnimation(animation);}

    public void rotar2(View view1) {
        Log.i("EJEMPLO", "Boton pulsado");
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.rotar2);
        animation.setRepeatMode(Animation.RESTART);
        animation.setRepeatCount(1);
        imagensota.startAnimation(animation);}




}

