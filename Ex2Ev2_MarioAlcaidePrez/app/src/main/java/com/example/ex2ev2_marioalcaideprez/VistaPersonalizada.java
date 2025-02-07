package com.example.ex2ev2_marioalcaideprez;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

public class VistaPersonalizada extends View {
    private Paint paintLine;
    private Paint paintText;
    private float[] posiciones = {
            92.8125f, 185.625f, 278.4375f, 371.25f, 464.0625f, 556.875f,
            649.6875f, 742.5f, 835.3125f, 928.125f, 1020.9375f, 1113.75f,
            1206.5625f, 1299.375f, 1392.1875f, 1485.0f, 1577.8125f, 1670.625f,
            1763.4375f, 1865.25f, 1940.0625f, 2041.875f, 2134.6875f
    };

    public VistaPersonalizada(Context context, AttributeSet attrs) {
        super(context, attrs);
        paintLine = new Paint();
        paintLine.setColor(Color.GREEN);
        paintLine.setStrokeWidth(15);
        paintLine.setPathEffect(new DashPathEffect(new float[]{10, 20}, 0));


        paintText = new Paint();
        paintText.setColor(Color.BLACK);
        paintText.setTextSize(20);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();

        for (float y : posiciones) {
            canvas.drawLine(50, y, width - 50, y, paintLine);
            canvas.drawText(y + "", 10, y, paintText);
        }

        paintText.setTextSize(40);
        canvas.drawText("fila: 544 scale= 3.09375", 120, 550, paintText);
        paintText.setTextSize(40);
        canvas.drawText("fila: 1088 width= " + width, 120, 950, paintText);
        paintText.setTextSize(40);
        canvas.drawText("fila: 1632 height= " + getHeight(), 120, 1350, paintText);
        paintText.setTextSize(40);
        canvas.drawText("ratio= " + ((float) getHeight() / width), 120, 1750, paintText);
    }
}

