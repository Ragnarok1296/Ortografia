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

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Constantes de la tabla LECCIONES
    public static final String TABLE_LESSONS =  "lessons";
    public static final String FIELD_MODULE = "module";
    public static final String FIELD_TITLE = "title";
    public static final String FIELD_VERSION = "version";
    public static final String FIELD_CONTENT = "content";

    //Tabla Lecciones
    public static final  String CREATE_TABLE_LESSONS = "CREATE TABLE " + TABLE_LESSONS + "("
            + "id INT PRIMARY KEY NOT NULL,"
            + FIELD_MODULE +" TEXT, "
            + FIELD_TITLE +" TEXT, "
            + FIELD_VERSION +" INT, "
            + FIELD_CONTENT +" TEXT);";


    //Contenido
    public static final String CONTENT_LESSONS = "Insert into lessons values " +
            "    (0,'Acentuación','Introducción',1,'En español, las palabras son tónicas o acentuadas cuando una de sus sílabas sobresale en intensidad cuando la pronunciamos. Estas palabras pueden tener tilde o no.@Comí\nCafés\nQuizás\nCalor');";




    //Tabla ejemplos


    //Contenido





}
