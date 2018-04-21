package com.ortografia.trinidad.models;

public class Utilities {

    //Database
    public static final String DATABASE =  "bdOrtografia";

    //Constantes de la tabla Users
    public static final String TABLE_USERS =  "users";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_LASTNAME = "lastName";
    public static final String FIELD_PASSWORD = "password";

    //Query para crear la tabla
    public static final  String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "("
            + FIELD_EMAIL +" TEXT PRIMARY KEY NOT NULL, "
            + FIELD_NAME +" TEXT, "
            + FIELD_LASTNAME +" TEXT, "
            + FIELD_PASSWORD +" TEXT);";

    //Constantes del usuario root
    public static final String ROOT_EMAIL = "Ragnarok";
    public static final String ROOT_NAME = "Root";
    public static final String ROOT_LASTNAME = "App";
    public static final String ROOT_PASSWORD = "1296";

    //Query para crear usuario root
    public static final String CREATE_USER_ROOT = "INSERT INTO " + TABLE_USERS + " VALUES "
            +"('" + ROOT_EMAIL +"','" + ROOT_NAME + "','" + ROOT_LASTNAME + "','" + ROOT_PASSWORD + "');";

}
