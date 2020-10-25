package com.example.practicasqlite;


import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class BaseDatos extends SQLiteOpenHelper {

    String tblusuario = "Create Table usuario (email text primary key, nombre text, clave text, rol text)";
    String tblmaterial = "Create Table material (idMat text primary key, nombre text, genero text)";

    public BaseDatos(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tblusuario);
        db.execSQL(tblmaterial);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE usuario");
        db.execSQL(tblusuario);
        db.execSQL("DROP TABLE material");
        db.execSQL(tblmaterial);
    }
}
