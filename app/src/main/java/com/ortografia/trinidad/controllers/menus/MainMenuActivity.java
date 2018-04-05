package com.ortografia.trinidad.controllers.menus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ortografia.trinidad.R;
import com.ortografia.trinidad.controllers.account.UpdateAccountActivity;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        final Button btnPrueba = (Button) findViewById(R.id.btnPrueba);

        btnPrueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainMenuActivity.this, UpdateAccountActivity.class);
                startActivity(i);
            }
        });

    }
}
