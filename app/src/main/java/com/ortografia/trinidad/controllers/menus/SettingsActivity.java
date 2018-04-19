package com.ortografia.trinidad.controllers.menus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.ortografia.trinidad.R;
import com.ortografia.trinidad.controllers.account.DeleteAccountActivity;
import com.ortografia.trinidad.controllers.account.UpdateAccountActivity;

public class SettingsActivity extends AppCompatActivity {

    //Elementos del activity
    ImageButton ibCancel;
    Button btnUpdateAccount;
    Button btnDeleteAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        hideBar();

        //Llamada a elementos del layout
        ibCancel = (ImageButton) findViewById(R.id.ibCancel);
        btnUpdateAccount = (Button) findViewById(R.id.btnUpdateAccount);
        btnDeleteAccount = (Button) findViewById(R.id.btnDeleteAccount);

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

}
