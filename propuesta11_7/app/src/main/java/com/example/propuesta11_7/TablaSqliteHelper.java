package com.example.propuesta11_7;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TablaSqliteHelper extends SQLiteOpenHelper {
    String sqlCreate = "CREATE TABLE Tabla " + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " + " campo1 TEXT, " + " campo2 TEXT, " +
            " campo3 TEXT, " + " campo4 TEXT )";

    public TablaSqliteHelper(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version){
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(sqlCreate);
        //se rellena la tabla con 10 registros
        for (int i = 1; i <= 10; i++){
            String campo1 = "Dato1-" + i;
            String campo2 = "Dato2-" + i;
            String campo3 = "Dato3-" + i;
            String campo4 = "Dato4-" + i;

            db.execSQL("INSERT INTO Tabla (campo1, campo2, campo3, campo4) " + "VALUES ('" + campo1 + "', '" + campo2 + "', '" + campo3 + "', '" + campo4 + "' ) ");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva){
        db.execSQL("DROP TABLE IF EXISTS Tabla");
        db.execSQL(sqlCreate);
    }
}

