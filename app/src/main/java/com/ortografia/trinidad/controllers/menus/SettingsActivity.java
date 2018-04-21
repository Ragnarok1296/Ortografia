package com.ortografia.trinidad.controllers.menus;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ortografia.trinidad.R;
import com.ortografia.trinidad.controllers.account.DeleteAccountActivity;
import com.ortografia.trinidad.controllers.account.UpdateAccountActivity;
import com.ortografia.trinidad.models.User;
import com.ortografia.trinidad.models.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class SettingsActivity extends AppCompatActivity {

    //Elementos del activity
    ImageButton ibCancel;
    Button btnUpdateAccount;
    Button btnDeleteAccount;
    TextView txtDelete;
    Button btnBackup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        hideBar();

        //Llamada a elementos del layout
        ibCancel = (ImageButton) findViewById(R.id.ibCancel);
        btnUpdateAccount = (Button) findViewById(R.id.btnUpdateAccount);
        txtDelete =(TextView) findViewById(R.id.txtDelete);
        btnDeleteAccount = (Button) findViewById(R.id.btnDeleteAccount);
        btnBackup = (Button) findViewById(R.id.btnBackupDB);


        //Regresar a la pantalla MainMenu
        ibCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //Ir a pantalla de actualizar
        btnUpdateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SettingsActivity.this, UpdateAccountActivity.class);
                startActivity(i);
            }
        });

        //Ir a pantalla de actualizar
        btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SettingsActivity.this, DeleteAccountActivity.class);
                startActivity(i);
            }
        });

        //Si el usuario que ingreso es root entonces quita la opcion eliminar cuenta
        if(User.getEmail().toString().equals(Utilities.ROOT_EMAIL)){
            btnDeleteAccount.setVisibility(View.GONE);
            txtDelete.setText(R.string.txt_backup_db);
            btnBackup.setVisibility(View.VISIBLE);
        }

        btnBackup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permission();
                createBackup();
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

    private void createBackup(){
        try {

            //Direccion de la base de datos
            File dirOrigin = Environment.getDataDirectory();
            String dirDbOrigin = "//data//" + getPackageName() + "//databases//" + Utilities.DATABASE;

            //Direccion a donde sera mandada la base de datos
            File dirDestination = Environment.getExternalStorageDirectory();
            String dirDbDestination = "Download/" + Utilities.DATABASE + ".db";

            if (dirDestination.canWrite()) {

                File dbCurrent = new File(dirOrigin, dirDbOrigin);
                File dbCopy = new File(dirDestination, dirDbDestination);

                if (dbCurrent.exists()) {

                    FileChannel src = new FileInputStream(dbCurrent).getChannel();
                    FileChannel dst = new FileOutputStream(dbCopy).getChannel();

                    //Se hace la copia del archivo
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();

                    Toast.makeText(this, R.string.backup_successful, Toast.LENGTH_SHORT).show();
                }

            }

        } catch (Exception e) {
            Toast.makeText(this, R.string.error_backup, Toast.LENGTH_SHORT).show();
        }
    }

    //GENERA EL MENSAJE PARA DAR PERMISOS DE ESCRITURA
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1 ;

    private void permission(){

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, R.string.toast_permissions_accept, Toast.LENGTH_SHORT).show();
                    createBackup();

                } else {

                    Toast.makeText(this, R.string.toast_permissions_decline, Toast.LENGTH_SHORT).show();

                }
                return;
            }
        }
    }


}
