package com.example.pelculas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Nombre del archivo de la base de datos y su versión
    private static final String DATABASE_NAME = "peliculas.db"; // Nombre del archivo SQLite
    private static final int DATABASE_VERSION = 1; // Versión de la base de datos, útil para actualizaciones

    // Nombre de la tabla y columnas
    private static final String TABLE_PELICULAS = "peliculas"; // Nombre de la tabla
    private static final String COLUMN_ID = "id"; // Columna para el ID único de cada película
    private static final String COLUMN_IMAGEN = "imagen"; // Columna para almacenar un identificador de recurso de la imagen
    private static final String COLUMN_NOMBRE = "nombre"; // Columna para el nombre de la película
    private static final String COLUMN_DIRECTOR = "director"; // Columna para el nombre del director
    private static final String COLUMN_DESCRIPCION = "descripcion"; // Columna para la descripción de la película
    private static final String COLUMN_VALORACION = "valoracion"; // Columna para la valoración de la película (float)

    // Constructor del helper
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // Aquí se inicializa la clase SQLiteOpenHelper con:
        // - `context`: contexto de la aplicación.
        // - `DATABASE_NAME`: nombre del archivo de la base de datos.
        // - `null`: usamos el cursor por defecto.
        // - `DATABASE_VERSION`: versión actual de la base de datos.
=======
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "usuarios.db";
    private static final int DATABASE_VERSION = 1;

    // Tabla de usuarios
    private static final String TABLE_USERS = "usuarios";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "nombre_usuario";
    private static final String COLUMN_PASSWORD = "contraseña";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
>>>>>>> 7ebd9fa258010fb9ce6e22456971748173e82513
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
<<<<<<< HEAD
        // Método llamado cuando se crea la base de datos por primera vez.

        // Sentencia SQL para crear la tabla de películas
        String createTable = "CREATE TABLE " + TABLE_PELICULAS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + // ID autoincremental
                COLUMN_IMAGEN + " INTEGER, " + // Almacenamos el ID del recurso de la imagen
                COLUMN_NOMBRE + " TEXT, " + // Nombre de la película
                COLUMN_DIRECTOR + " TEXT, " + // Nombre del director
                COLUMN_DESCRIPCION + " TEXT, " + // Descripción de la película
                COLUMN_VALORACION + " REAL)"; // Valoración (puntuación)
        db.execSQL(createTable); // Ejecutamos la sentencia SQL para crear la tabla
=======
        // Crear la tabla de usuarios
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USERNAME + " TEXT UNIQUE, "
                + COLUMN_PASSWORD + " TEXT)";
        db.execSQL(CREATE_USERS_TABLE);
>>>>>>> 7ebd9fa258010fb9ce6e22456971748173e82513
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
<<<<<<< HEAD
        // Este método se llama si la base de datos ya existe, pero su versión cambia.
        // Por ejemplo, si actualizamos la versión de 1 a 2.

        // Elimina la tabla existente si ya existe
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PELICULAS);

        // Llama de nuevo al método onCreate para recrear la tabla
        onCreate(db);
    }

    // Método para insertar una nueva película en la base de datos
    public void insertPelicula(Peliculas pelicula) {
        // Obtenemos una instancia de la base de datos en modo escritura
        SQLiteDatabase db = this.getWritableDatabase();

        // Usamos ContentValues para preparar los datos que queremos insertar
        ContentValues values = new ContentValues();
        values.put(COLUMN_IMAGEN, pelicula.getImagenResId()); // Añadimos la imagen
        values.put(COLUMN_NOMBRE, pelicula.getNombre()); // Añadimos el nombre
        values.put(COLUMN_DIRECTOR, pelicula.getDirector()); // Añadimos el director
        values.put(COLUMN_DESCRIPCION, pelicula.getResumen()); // Añadimos la descripción
        values.put(COLUMN_VALORACION, pelicula.getValoracion()); // Añadimos la valoración

        // Insertamos los datos en la tabla
        db.insert(TABLE_PELICULAS, null, values);

        // Cerramos la conexión a la base de datos
        db.close();
    }

    // Método para obtener todas las películas de la base de datos
    public List<Peliculas> getAllPeliculas() {
        // Lista para almacenar las películas recuperadas
        List<Peliculas> lista = new ArrayList<>();

        // Obtenemos una instancia de la base de datos en modo lectura
        SQLiteDatabase db = this.getReadableDatabase();

        // Realizamos una consulta para obtener todos los datos de la tabla
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PELICULAS, null);

        // Recorremos el cursor para leer los datos fila por fila
        if (cursor.moveToFirst()) {
            do {
                // Creamos un objeto Peliculas con los datos de cada fila
                Peliculas pelicula = new Peliculas(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IMAGEN)), // Imagen
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE)), // Nombre
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DIRECTOR)), // Director
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPCION)), // Descripción
                        cursor.getFloat(cursor.getColumnIndexOrThrow(COLUMN_VALORACION)) // Valoración
                );

                // Añadimos la película a la lista
                lista.add(pelicula);
            } while (cursor.moveToNext()); // Avanzamos a la siguiente fila
        }

        // Cerramos el cursor y la conexión a la base de datos
        cursor.close();
        db.close();

        // Devolvemos la lista de películas
        return lista;
=======
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    // Método para insertar un nuevo usuario
    public boolean insertarUsuario(String nombreUsuario, String contraseña) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, nombreUsuario);
        values.put(COLUMN_PASSWORD, contraseña);

        long result = db.insert(TABLE_USERS, null, values);
        db.close();

        // Devuelve true si la inserción fue exitosa
        return result != -1;
    }

    // Método para validar usuarios
    public boolean validarUsuario(String nombreUsuario, String contraseña) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,
                new String[]{COLUMN_ID},
                COLUMN_USERNAME + "=? AND " + COLUMN_PASSWORD + "=?",
                new String[]{nombreUsuario, contraseña},
                null, null, null);

        boolean isValid = cursor.moveToFirst();
        cursor.close();
        db.close();
        return isValid;
>>>>>>> 7ebd9fa258010fb9ce6e22456971748173e82513
    }
}
