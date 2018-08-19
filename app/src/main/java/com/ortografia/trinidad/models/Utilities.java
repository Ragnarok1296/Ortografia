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
            "(2,'Acentuación','Introducción',3,'Las palabras se clasifican según el lugar que ocupe la sílaba tónica. Existen cuatro categorías de palabras: agudas, llanas o graves, esdrújulas y sobresdrújulas.')," +
            "(3,'Diferencias','G-J',1,'En castellano se produce a veces confusión entre estas dos consonantes: \"g\" y \"j\".  Ambas letras se pronuncian exactamente igual delante de las vocales \"e\" e \"i\".@Jirafa\nGeranio\nJerez\nGema\nJesuita\nGimnasia')," +
            "(4,'Diferencias','G-J',2,'Para el uso de la g, existen algunas reglas para tener en consideración:\n\n1. Después de al y de las consonantes n o r, se escribe con Ge, Gi y no con je, ji.\n\n2. Las palabras que contienen la silaba gen.\n\n3. Las palabras que comienzan o terminan con el prefijo geo.\n\n4. Las palabras que empiezan por legi, legis, gest.\n\n5. Las formas verbales de los verbos cuyo infinitivo termina en ger, gir o gerar se escriben con G cuando llevan ge o gi.\n\n6. Las palabras que contienen los grupos -igi-, -agi-.\n\n7. Las palabras terminadas en -gio, -gion, -gional, -gionario, -gioso.\n\n8. Las palabras terminadas en -igeno, -igero.')," +
            "(5,'Diferencias','G-J',3,'Así mismo, existen reglas para el uso de la letra j. Las cuales son:\n\n1. Las palabras derivadas de sustantivos o adjetivos terminados en ja, jo.\n\n2. Las palabras que comienzan por adj y por obj.\n\n3. Las palabras terminadas en aje y en eje.\n\n4. Las formas verbales pretéritas y subjuntivas de los verbos cuyo infinitivo termina en decir, ducir, traer.\n\n5. Las formas verbales de los verbos cuyo infinitivo termina en jar o jear.\n\n6. Los sustantivos y adjetivos terminados en jero, jera, jería.\n\n7. Las palabras derivadas del latín subjectare (someter, poner debajo).')," +
            "(6,'Puntuación','Punto',1,'El signo ortográfico del \"punto\" se utiliza para cerrar partes de la comunicación que tienen sentido en sí mismo.\n1. Existen varios tipos de puntos: punto y seguido, punto y aparte, punto final en un escrito.@Después de salir a la calle, comenzó a llover y como no llevaba impermeable, decidí entrar en una tienda y comprarme uno.')," +
            "(7,'Puntuación','Punto',2,'El Punto y seguido separa, dentro de un mismo párrafo, enunciados que están relacionados entre sí (desarrollan una misma idea).@Después de salir a la calle, comenzó a llover y como no llevaba impermeable, decidí entrar en una tienda y comprarme uno. Pero cuando entré en la tienda me di cuenta de que no llevaba dinero. Decidí entonces que mejor sería volver a casa y esperar a que escampase.')," +
            "(8,'Puntuación','Punto',3,'Se usa punto final cuando termina (acaba) un escrito o una división importante de un texto (parte, capítulo, sección, etc.).');";


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
            "(2,2,1,'¿En cuantas categorías se clasifican las palabras de acuerdo a la ubicación de la silaba tónica?')," +
            "(3,3,1,'¿Frente a que vocal ambas consonantes (“g” y “j”) se pronuncian igual?@O@I@A@C')," +
            "(4,4,1,'Selecciona las palabras que están escritas correctamente:@Oxígeno@Coleguio@Exigir@Ajitar@Algebra@Pajina@Exijir@General')," +
            "(5,5,1,'Selecciona las palabras que están escritas incorrectamente:@Flogera@Cajero@Canjear@Exijir')," +
            "(6,6,1,'¿Cuántos tipos de punto hay?')," +
            "(7,7,1,'En el enunciado siguiente, ¿Los puntos están colocados correctamente?\nLlame a la oficina de San Juan antes de las doce. Hay que reconfirmar el pedido. El mismo debe recibirse mañana.@Si@No')," +
            "(8,8,1,'El punto final se usa para:@Acabar un capítulo de libro.@Terminar un párrafo y seguir escribiendo en otro.@Finalizar una carta.@Terminar con tu pareja.');";

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
