package com.example.propuesta11_7;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private MediaRecorder grabador;
    private MediaPlayer reproductor;
    private Button botonGrabar, botonParar, botonPlay;
    private SurfaceView surface;
    private SurfaceHolder surfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        surface.getHolder().addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        Button botonGrabar = findViewById(R.id.botonGrabar);
        Button botonParar = findViewById(R.id.botonParar);
        Button botonPlay = findViewById(R.id.botonPlay);
        SurfaceView surface = findViewById(R.id.pantalla);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3){

    }

    public void surfaceCreated(SurfaceHolder arg0){
        if (grabador == null){
            grabador = new MediaRecorder();
            grabador.setPreviewDisplay(surfaceHolder.getSurface());
        }

        if (reproductor == null){
            reproductor = new MediaPlayer();
            reproductor.setDisplay(surfaceHolder);
        }

    }

    public void surfaceDestroyed(SurfaceHolder arg0){

    }

    botonGrabar.setOnClickListener(new View.OnClickListener(){
        public 


    })

    @Override
    protected void onDestroy() {
        super.onDestroy();

        grabador.release();
        reproductor.release();
    }
}