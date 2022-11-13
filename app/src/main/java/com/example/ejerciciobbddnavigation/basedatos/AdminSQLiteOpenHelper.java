package com.example.ejerciciobbddnavigation.basedatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//Definimos la clase AdminSQLiteOpenHelper que extiende de SQLiteOpenHelper
public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //Generamos los dos métodos que nos obliga a implementar donde el primero de ellos es donde tenemos
    //que crear la tabla de la base de datos
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table alumnos(dni String(9) primary key, nombre String(50), apellidos String(50), sexo String(6))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
