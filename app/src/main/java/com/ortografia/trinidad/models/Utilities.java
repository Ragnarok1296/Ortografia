package com.ortografia.trinidad.models;

public class Utilities {

    //Database
    public static final String DATABASE =  "bdOrtografia";

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
    public static final String CONTENT_LESSONS = "Insert into "+ TABLE_LESSONS + " values " +
            "(0,'Acentuación','Introducción',1,'En español, las palabras son tónicas o acentuadas cuando una de sus sílabas sobresale en intensidad cuando la pronunciamos. Estas palabras pueden tener tilde o no.@Comí\nCafés\nQuizás\nCalor')," +
            "(1,'Acentuación','Introducción',2,'El acento se define como la fuerza o intensidad mayor con la que se destaca una sílaba dentro de una palabra. Existen dos clases de acento: el prosódico (tónico o de intensidad), que se encuentra en todas las palabras, y el ortográfico (tilde), que es el acento representado en la escritura (´).@Habló\nRecepción\nPreguntar\nCasualidad')," +
            "(2,'Acentuación','Introducción',3,'Las palabras se clasifican según el lugar que ocupe la sílaba tónica. Existen cuatro categorías de palabras: agudas, llanas o graves, esdrújulas y sobresdrújulas.');";

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Tabla ejemplos
    public static final String TABLE_EXAMPLES =  "examples";
    public static final String FIELD_IDLESSON = "idLesson";
    public static final String FIELD_VERSIONLESSON = "versionLesson";
    public static final String FIELD_CONTENT_EXAMPLES = "content";

    public static final  String CREATE_TABLE_EXAMPLES = "CREATE TABLE " + TABLE_EXAMPLES + "("
            + "id INT PRIMARY KEY NOT NULL,"
            + FIELD_IDLESSON +" INT, "
            + FIELD_VERSIONLESSON +" INT, "
            + FIELD_CONTENT_EXAMPLES +" TEXT);";

    //Contenido
    public static final String CONTENT_EXAMPLES = "Insert into "+ TABLE_EXAMPLES + " values " +
            "(0,0,1,'En español, las palabras pueden ser:@Tónicas@Diptongos@Acentuadas@Silabas')," +
            "(1,1,1,'La tilde es característico del tipo de acento…@Tónico@Ortográfico')," +
            "(2,2,1,'¿En cuantas categorías se clasifican las palabras de acuerdo a la ubicación de la silaba tónica?');";

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Tabla ejemplos
    public static final String TABLE_QUIZZES =  "quizzes";
    public static final String FIELD_MODULE_QUIZ = "module";
    public static final String FIELD_VERSION_QUIZ = "version";
    public static final String FIELD_CONTENT_QUIZ = "content";

    public static final  String CREATE_TABLE_QUIZZES = "CREATE TABLE " + TABLE_QUIZZES + "("
            + "id INT PRIMARY KEY NOT NULL,"
            + FIELD_MODULE_QUIZ +" TEXT, "
            + FIELD_VERSION_QUIZ +" INT, "
            + FIELD_CONTENT_QUIZ +" TEXT);";

    //Contenido
    public static final String CONTENT_QUIZZES = "Insert into "+ TABLE_QUIZZES + " values " +
            "(0,'Acentuación',1,'1.- Escribe correctamente la palabra “Modulo”')," +
            "(1,'Acentuación',2,'2.- Selecciona las palabras que No tienen errores de acentuación@Lámpara@Ámor@Géneral@Página@Mexíco')," +
            "(2,'Acentuación',3,'3.- Todas las palabras llevan tilde, es una afirmación…@Verdadera@Falsa')," +
            "(3,'Acentuación',4,'4.- Las palabras se clasificación de acuerdo a la posición del acento. Selecciona las categorías de esa clasificación.@Agudas@Esdrújulas@Acentuadas@Sobresdrújulas@Tónicas@Graves')," +
            "(4,'Acentuación',5,'5.- La palabra compás es un tipo de palabra…@Grave@Llana@Esdrújula@Aguda@Sobresdrújula');";









}
