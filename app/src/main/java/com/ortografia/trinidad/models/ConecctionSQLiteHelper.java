package com.ortografia.trinidad.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConecctionSQLiteHelper extends SQLiteOpenHelper {

    public ConecctionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Cuando se crea la clase (la base de datos), se crean las tablas
        db.execSQL(Utilities.CREATE_TABLE_USERS);
        db.execSQL(Utilities.CREATE_USER_ROOT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Al momento de volver a instalar la aplicacion, si la tabla existe entonces procede a borrarla y crear una nueva
        //para tener una nueva version de la BD
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

}