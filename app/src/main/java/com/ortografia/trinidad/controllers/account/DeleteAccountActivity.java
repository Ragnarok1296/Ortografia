package com.ortografia.trinidad.controllers.account;

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
import com.ortografia.trinidad.models.User;
import com.ortografia.trinidad.models.Utilities;

public class DeleteAccountActivity extends AppCompatActivity {

    //Creacion del objeto conexion
    ConecctionSQLiteHelper conn;

    //Elementos del activity
    EditText etEmail;
    EditText etPassword;
    EditText etRepeatPassword;
    ImageButton ibCancel;
    Button btnDeleteAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);
        hideBar();

        //Instanciacion de la conexion
        conn = new ConecctionSQLiteHelper(this, Utilities.DATABASE, null, 1);

        //Llamada a elementos del layout
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etRepeatPassword = (EditText) findViewById(R.id.etRepeatPassword);
        ibCancel = (ImageButton) findViewById(R.id.ibCancel);
        btnDeleteAccount = (Button) findViewById(R.id.btnDeleteAccount);

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

        //Regresar a la pantalla Settings
        ibCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //Evento clic en actualizar
        btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Verificar si algun campo esta vacio
                if(!"".equals(etEmail.getText().toString()) &&
                        !"".equals(etPassword.getText().toString()) &&
                        !"".equals(etRepeatPassword.getText().toString()) ){

                    if(etEmail.getText().toString().equals(User.getEmail().toString())) {
                        if (etPassword.getText().toString().equals(etRepeatPassword.getText().toString())) {
                            if (etPassword.getText().toString().equals(User.getPassword().toString()))
                                question();
                            else
                                wrongPassword();
                        }else
                            wrongRepeatPassword();
                    }else
                        wrongUser();

                }else
                    alertWarning();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

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

    //Actualizar datos de la cuenta
    private void deleteUser() {

        try{

            //Se inicia la conexion
            SQLiteDatabase db = conn.getWritableDatabase();

            //Se indica cual es el valor que sera utilizado en el where
            String[] parameters = {etEmail.getText().toString()};

            //Se elimina de la tabla el registro
            db.delete(Utilities.TABLE_USERS,Utilities.FIELD_EMAIL + "=?",parameters);

            //Se cierra la conexion
            db.close();

            alertCorrect();

        }catch(Exception e) {

            errorCreateAccount();

        }

    }

    //Alerta para indicar si la eliminacion fue exitosa
    public void alertCorrect() {
        new AlertDialog.Builder(DeleteAccountActivity.this)
                .setTitle(R.string.title_bye)
                .setMessage(R.string.delete_successful)
                .setIcon(R.drawable.correct)
                .setPositiveButton(R.string.positive_button_ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(DeleteAccountActivity.this, LoginActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK );
                                startActivity(i);
                                dialog.cancel();
                            }
                        })
                .show();
    }

    //Alerta para indicar que se llenen todos los campos
    public void alertWarning() {

        new AlertDialog.Builder(DeleteAccountActivity.this)
                .setTitle(R.string.title_warning)
                .setMessage(R.string.fill_all_fields)
                .setIcon(R.drawable.warning)
                .setPositiveButton(R.string.positive_button_ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                .show();

    }

    //Error por si la aplicacion fallo al eliminar
    public void errorCreateAccount(){

        new AlertDialog.Builder(DeleteAccountActivity.this)
                .setTitle(R.string.title_error)
                .setMessage(R.string.try_again)
                .setIcon(R.drawable.error)
                .setPositiveButton(R.string.positive_button_ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                .show();

    }

    //Alerta para indicar que el email no coincide con la cuenta
    public void wrongUser() {

        new AlertDialog.Builder(DeleteAccountActivity.this)
                .setTitle(R.string.title_warning)
                .setMessage(R.string.emailIncorrect)
                .setIcon(R.drawable.error)
                .setPositiveButton(R.string.positive_button_ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                .show();

    }

    //Alerta para indicar que las dos contraseñas son diferentes
    public void wrongRepeatPassword() {

        new AlertDialog.Builder(DeleteAccountActivity.this)
                .setTitle(R.string.title_warning)
                .setMessage(R.string.passwordRepeatIncorrect)
                .setIcon(R.drawable.warning)
                .setPositiveButton(R.string.positive_button_ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                .show();

    }

    //Alerta para indicar que la contraseña es incorrecta
    public void wrongPassword() {

        new AlertDialog.Builder(DeleteAccountActivity.this)
                .setTitle(R.string.title_warning)
                .setMessage(R.string.passwordIncorrect)
                .setIcon(R.drawable.warning)
                .setPositiveButton(R.string.positive_button_ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                .show();

    }

    //Desicion
    public void question() {
        new AlertDialog.Builder(DeleteAccountActivity.this)
                .setTitle(R.string.title_warning)
                .setMessage(R.string.are_you_sure)
                .setIcon(R.drawable.warning)
                .setPositiveButton(R.string.positive_button_yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                deleteUser();
                                dialog.cancel();
                            }
                        })
                .setNeutralButton(R.string.neutral_button_cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                            }
                        })
                .setNegativeButton(R.string.negative_button_no,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                onBackPressed();
                                dialog.cancel();
                            }
                        })
                .show();
    }

}
