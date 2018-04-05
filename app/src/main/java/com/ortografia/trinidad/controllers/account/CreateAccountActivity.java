package com.ortografia.trinidad.controllers.account;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ortografia.trinidad.LoginActivity;
import com.ortografia.trinidad.R;
import com.ortografia.trinidad.models.ConecctionSQLiteHelper;
import com.ortografia.trinidad.models.Utilities;

public class CreateAccountActivity extends AppCompatActivity {

    //Creacion del objeto conexion
    ConecctionSQLiteHelper conn;

    //Elementos del activity
    EditText etName;
    EditText etLastName;
    EditText etEmail;
    EditText etPassword;
    ImageButton ibCancel;
    Button btnCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        hideBar();

        //Instanciacion de la conexion
        conn = new ConecctionSQLiteHelper(this, "bdOrtografia", null, 1);

        //Llamada a elementos del layout
        etName = (EditText) findViewById(R.id.etName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        ibCancel = (ImageButton) findViewById(R.id.ibCancel);
        btnCreateAccount = (Button) findViewById(R.id.btnCreateAccount);

        //Volver a la inmersion de pantalla completa
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

        //Regresar a la pantalla de login
        ibCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreateAccountActivity.this,LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK );
                startActivity(i);
            }
        });

        //Evento clic en crear cuenta
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Verificar si los campos estan llenos
                if(!"".equals(etName.getText().toString()) &&
                        !"".equals(etLastName.getText().toString()) &&
                        !"".equals(etEmail.getText().toString()) &&
                        !"".equals(etPassword.getText().toString()) ){

                    registerUser();

                }else
                    alertWarning("NullFieldsWarning");
            }
        });

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

    //Refistrar usuario
    private void registerUser() {

        try{

            //Se abre la conexion con la bd
            SQLiteDatabase db = conn.getWritableDatabase();

            //Se guardan los valores a insertar en un ContentValues
            ContentValues values = new ContentValues();
            values.put(Utilities.FIELD_EMAIL,etEmail.getText().toString());
            values.put(Utilities.FIELD_NAME,etName.getText().toString());
            values.put(Utilities.FIELD_LASTNAME,etLastName.getText().toString());
            values.put(Utilities.FIELD_PASSWORD,etPassword.getText().toString());

            //Se inserta y se almacena el id del registro insertado
            Long idResult = db.insert(Utilities.TABLE_USERS,Utilities.FIELD_EMAIL,values);

            //Cerrar conexion
            db.close();

            // -1 = Ya existe usuario
            if(idResult == -1)
                alertWarning("EmailWarning");
            else
                alertCorrect();

        }catch(Exception e) {

            errorCreateAccount();

        }

    }

    //Alerta indicando que el registro fue exitoso
    public void alertCorrect() {
        new AlertDialog.Builder(CreateAccountActivity.this)
                .setTitle("Felicidades")
                .setMessage("Registro Exitoso")
                .setIcon(R.drawable.correct)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(CreateAccountActivity.this,LoginActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK );
                                startActivity(i);
                                dialog.cancel();
                            }
                        })
                .show();
    }

    //Alerta indicando si no se llenaron todos los campos o si el email ya esta registrado
    public void alertWarning(String option) {

        if("NullFieldsWarning".equals(option)){

            new AlertDialog.Builder(CreateAccountActivity.this)
                    .setTitle("Warning")
                    .setMessage("Llene todos los campos!!!")
                    .setIcon(R.drawable.warning)
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            })
                    .show();

        }
        else if("EmailWarning".equals(option)){

            new AlertDialog.Builder(CreateAccountActivity.this)
                    .setTitle("Warning")
                    .setMessage("El email ya existe!!!")
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

    //Error por si la aplicacion fallo al registar
    public void errorCreateAccount(){

        new AlertDialog.Builder(CreateAccountActivity.this)
                .setTitle("Error")
                .setMessage("La aplicacion fallo, intantalo nuevamente!!!")
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
