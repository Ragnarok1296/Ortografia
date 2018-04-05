package com.ortografia.trinidad.models;

public class Utilities {

    //Contantes de la tabla Users
    public static final String TABLE_USERS =  "users";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_LASTNAME = "lastName";
    public static final String FIELD_PASSWORD = "password";

    public static final  String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "("
            + FIELD_EMAIL +" TEXT PRIMARY KEY NOT NULL, "
            + FIELD_NAME +" TEXT, "
            + FIELD_LASTNAME +" TEXT, "
            + FIELD_PASSWORD +" TEXT)";



}
