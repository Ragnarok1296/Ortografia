package com.ortografia.trinidad;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.ortografia.trinidad.models.User;
import com.ortografia.trinidad.models.Utilities;

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
        hideBar();

        //Instanciacion de la conexion
        conn = new ConecctionSQLiteHelper(this, "bdOrtografia", null, 1);

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

    //Alert dialog en caso de que, o el password sea incorrecto o que el email no exista
    public void errorLogin(String option){

        if("ErrorPassword".equals(option)) {

            new AlertDialog.Builder(LoginActivity.this)
                    .setTitle("Error")
                    .setMessage(R.string.passwordIncorrect)
                    .setIcon(R.drawable.error)
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            })
                    .show();

        }

        if("AccounNoExist".equals(option)) {

            new AlertDialog.Builder(LoginActivity.this)
                    .setTitle("Error")
                    .setMessage("Lo sentimos, no pudimos encontrar tu cuenta.")
                    .setIcon(R.drawable.error)
                    .setPositiveButton("OK",
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
                .setTitle("Warning")
                .setMessage(R.string.fill_two_fields)
                .setIcon(R.drawable.warning)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                .show();

    }

}
