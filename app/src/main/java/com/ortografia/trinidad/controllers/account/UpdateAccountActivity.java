package com.ortografia.trinidad.controllers.account;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ortografia.trinidad.LoginActivity;
import com.ortografia.trinidad.R;
import com.ortografia.trinidad.controllers.menus.MainMenuActivity;
import com.ortografia.trinidad.models.ConecctionSQLiteHelper;
import com.ortografia.trinidad.models.User;
import com.ortografia.trinidad.models.Utilities;

public class UpdateAccountActivity extends AppCompatActivity {

    //Conexion a base de datos
    ConecctionSQLiteHelper conn;

    //Elementos del activity
    EditText etName;
    EditText etLastName;
    TextView txtEmail;
    EditText etPassword;
    ImageButton ibCancel;
    Button btnUpdateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_account);
        hideBar();

        //Conexion a base de datos
        conn = new ConecctionSQLiteHelper(this, "bdOrtografia", null, 1);

        //Llamada a elementos del layout
        etName = (EditText) findViewById(R.id.etName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        ibCancel = (ImageButton) findViewById(R.id.ibCancel);
        btnUpdateAccount = (Button) findViewById(R.id.btnCreateAccount);

        //Llenado de los campos
        etName.setText(User.getName());
        txtEmail.setText(User.getEmail());
        etLastName.setText(User.getLastName());
        etPassword.setText(User.getPassword());

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

        //Regresar a la pantalla MainMenu
        ibCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Buscar como saber en que pantalla invocaron esta
                Intent i = new Intent(UpdateAccountActivity.this,MainMenuActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK );
                startActivity(i);
            }
        });

        //Evento clic en actualizar
        btnUpdateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Verificar si algun campo esta vacio
                if(!"".equals(etName.getText().toString()) &&
                        !"".equals(etLastName.getText().toString()) &&
                        !"".equals(txtEmail.getText().toString()) &&
                        !"".equals(etPassword.getText().toString()) ){

                    updateUser();

                }else
                    alertWarning();
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus)
            hideBar();
    }

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

    private void updateUser() {

        try{

            SQLiteDatabase db = conn.getWritableDatabase();
            String[] parameters = {txtEmail.getText().toString()};
            ContentValues values = new ContentValues();

            values.put(Utilities.FIELD_NAME,etName.getText().toString());
            values.put(Utilities.FIELD_LASTNAME,etLastName.getText().toString());
            values.put(Utilities.FIELD_PASSWORD,etPassword.getText().toString());

            db.update(Utilities.TABLE_USERS,values,Utilities.FIELD_EMAIL+"=?",parameters);

            db.close();

            alertCorrect();

        }catch(Exception e) {

            errorCreateAccount();

        }

    }

    public void alertCorrect() {
        new AlertDialog.Builder(UpdateAccountActivity.this)
                .setTitle("Felicidades")
                .setMessage("Actializacion Exitosa")
                .setIcon(R.drawable.correct)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(UpdateAccountActivity.this,MainMenuActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK );
                                startActivity(i);
                                dialog.cancel();
                            }
                        })
                .show();
    }

    public void alertWarning() {

        new AlertDialog.Builder(UpdateAccountActivity.this)
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

    public void errorCreateAccount(){

        new AlertDialog.Builder(UpdateAccountActivity.this)
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
