package com.ortografia.trinidad;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ortografia.trinidad.controllers.account.CreateAccountActivity;
import com.ortografia.trinidad.controllers.menus.MenuActivity;
import com.ortografia.trinidad.models.ConecctionSQLiteHelper;
import com.ortografia.trinidad.models.NotificationService;
import com.ortografia.trinidad.models.User;
import com.ortografia.trinidad.models.Utilities;

import java.util.Calendar;
import java.util.Date;

public class LoginActivity extends AppCompatActivity {

    //Tiempo que se le dara para hacer el segundo clic para salir
    private static long presionado;

    //Creacion del objeto conexion
    ConecctionSQLiteHelper conn;

    //Elementos a utilizar en el activity
    EditText etEmail;
    EditText etPassword;
    Button btnLogin;
    FloatingActionButton btnCreateNewAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Codigo para verificar si ya se habia iniciado sesion y asi mandar al menu
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("TAG", Context.MODE_PRIVATE);
        String valor = sharedPref.getString("Status", "LogOut");

        // Si el valor guardo es diferente al valor por defecto, significa que ya inicio sesio asi que lo puedes enviar al main
        if (valor.equals("LogIn")) {

            User.setEmail(sharedPref.getString("Email", ""));
            User.setName(sharedPref.getString("Name", ""));
            User.setLastName(sharedPref.getString("LastName", ""));
            User.setPassword(sharedPref.getString("Password", ""));

            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK );
            startActivity(intent);
        }

        //Se inicia la inmersion completa
        hideBar();

        //se crean las 3 notificaciones en diferente hora
        notification(9,0,0,0);
        notification(15,0,0,1);
        notification(21,0,0,2);

        //Instanciacion de la conexion
        conn = new ConecctionSQLiteHelper(this, Utilities.DATABASE, null, 1);

        //Asignacion de elementos del layout
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnCreateNewAccount = (FloatingActionButton) findViewById(R.id.fabCreate);

        //Inmersion despues de terminar de escribir en el editText
        etPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER
                        || actionId == EditorInfo.IME_ACTION_DONE) {
                    hideBar();
                }
                return false;
            }
        });

        //Ir a la actividad CreateAccount
        btnCreateNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,CreateAccountActivity.class);
                startActivity(i);
            }
        });

        //Boton para entrar
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Verifica que los dos campos esten llenos
                if(!"".equals(etEmail.getText().toString()) && !"".equals(etPassword.getText().toString()) )
                    consult();
                else
                    alertWarning();

            }
        });

    }

    @Override
    public void onBackPressed() {
        //Este es el evento para el doble clic
        if (presionado + 2000 > System.currentTimeMillis())
            super.onBackPressed();
        else
            Toast.makeText(this, R.string.exitApplicattion , Toast.LENGTH_SHORT).show();
        presionado = System.currentTimeMillis();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus)
            hideBar();
    }

    //Oculta las barras para la inmersion completa
    public void hideBar(){
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    //Hace la consulta del email introducido
    public void consult(){
        //Se inicia la base de datos
        SQLiteDatabase db = conn.getReadableDatabase();

        //Se selecciona el(los) parametros a utilizar en el where
        String[] parameters = {etEmail.getText().toString()};
        //Se indican los campos que se quieren obtener
        String[] fields = {Utilities.FIELD_NAME, Utilities.FIELD_LASTNAME, Utilities.FIELD_PASSWORD};

        try{
            //Se ejecuta el query y se guarda en un cursosr
            Cursor cursor = db.query(Utilities.TABLE_USERS,fields,Utilities.FIELD_EMAIL+"=?",parameters,null,null,null);

            //Verifica si arrojo un resultado
            if(cursor.getCount() == 1){

                //Se obtienen los datos arrojados
                cursor.moveToFirst();
                String name = cursor.getString(0);
                String lastName = cursor.getString(1);
                String password = cursor.getString(2);

                //Si el password que esta en la base de datos es igual introducido, entonces:
                if(etPassword.getText().toString().equals(password)){

                    //Se guardan los datos de usuario
                    User.setName(name.toString());
                    User.setEmail(etEmail.getText().toString());
                    User.setLastName(lastName.toString());
                    User.setPassword(password.toString());

                    //Se guarda una bandera para que asi al siguiente inicio no se tenga que estar llenando el login y entrar al menu
                    SharedPreferences sharedPrefs = getApplicationContext().getSharedPreferences("TAG", Context.MODE_PRIVATE);
                    sharedPrefs.edit().putString("Status","LogIn").apply();

                    sharedPrefs.edit().putString("Email",User.getEmail().toString()).apply();
                    sharedPrefs.edit().putString("Name",User.getName().toString()).apply();
                    sharedPrefs.edit().putString("LastName",User.getLastName().toString()).apply();
                    sharedPrefs.edit().putString("Password",User.getPassword().toString()).apply();


                    //Se inicia la actividad MainMenu
                    Intent i = new Intent(LoginActivity.this,MenuActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK );
                    startActivity(i);

                }else
                    errorLogin("ErrorPassword");

            } else
                errorLogin("AccounNoExist");

            db.close();

        }catch (Exception e){

            Toast.makeText(getApplicationContext(),R.string.errorApplicattion,Toast.LENGTH_SHORT).show();

        }

    }

    //Creacion de notificacion
    private void notification(int hour, int min, int sec,int code ){

        //Se genera la hora en la que aparecera la notificacion
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.SECOND, sec);
        //esta se encarga de que al abrir la aplicacion nuevamente se muestren las notificaciones
        if (calendar.getTime().compareTo(new Date()) < 0) calendar.add(Calendar.DAY_OF_MONTH, 1);

        //Se crea el intent para llamara a la clase NotificatioService
        Intent intent = new Intent(getApplicationContext(), new NotificationService().getClass());
        //Se le asigna un codigo especial para que no se sobreescriba la notificacion
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), code, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //Se crea la alarma y se procede a dar los parametros que anteriormente se crearon
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),24*60*60*1000,pendingIntent);

    }

    //Alert dialog en caso de que, o el password sea incorrecto o que el email no exista
    public void errorLogin(String option){

        if("ErrorPassword".equals(option)) {

            new AlertDialog.Builder(LoginActivity.this)
                    .setTitle(R.string.title_error)
                    .setMessage(R.string.passwordIncorrect)
                    .setIcon(R.drawable.error)
                    .setPositiveButton(R.string.positive_button_ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            })
                    .show();

        }

        if("AccounNoExist".equals(option)) {

            new AlertDialog.Builder(LoginActivity.this)
                    .setTitle(R.string.title_error)
                    .setMessage(R.string.account_no_found)
                    .setIcon(R.drawable.error)
                    .setPositiveButton(R.string.positive_button_ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            })
                    .show();

        }

    }

    //Alert dialog para indicar al usuario que llene los dos campos
    public void alertWarning() {

        new AlertDialog.Builder(LoginActivity.this)
                .setTitle(R.string.title_warning)
                .setMessage(R.string.fill_two_fields)
                .setIcon(R.drawable.warning)
                .setPositiveButton(R.string.positive_button_ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                .show();

    }

}
