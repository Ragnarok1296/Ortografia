package com.ortografia.trinidad.controllers.menus;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ortografia.trinidad.LoginActivity;
import com.ortografia.trinidad.R;
import com.ortografia.trinidad.controllers.account.UpdateAccountActivity;
import com.ortografia.trinidad.models.User;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //Tiempo que se le dara para hacer el segundo clic para salir
    private static long presionado;

    //Se necesita este manager para cargar los fragments
    android.support.v4.app.FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Se inicializa el manager
        fragmentManager = getSupportFragmentManager();

        //Se carga el toolbar que se creo
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Se carga el menu desplegable
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Se carga el fragmeto de menu principal
        fragmentManager.beginTransaction().replace(R.id.container, new MainMenuFragment()).commit();

        //Se carga el nombre del usuario en el texto para darle la bienvenida
        TextView txtUser = (TextView) navigationView.getHeaderView(0).findViewById(R.id.txtUserNavView);
        txtUser.setText(User.getName().toString() + " " + User.getLastName().toString());

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        //Si esta abierto el menu desplegable , entonces con el boton de atras se cierra
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //Si tiene fragments simplemente ejecuta la pulsación Back normal
            if (fragmentManager.getBackStackEntryCount()>0)
                super.onBackPressed();
            else {
                //Este es el evento para el doble clic
                if (presionado + 2000 > System.currentTimeMillis())
                    super.onBackPressed();
                else
                    Toast.makeText(this, R.string.exitApplicattion , Toast.LENGTH_SHORT).show();
                presionado = System.currentTimeMillis();
            }
        }

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //Se obtiene el id del elemento seleccionado
        int id = item.getItemId();

        //Se compara con las opciones para poder cargar la activity o el fragment
        if (id == R.id.nav_mainmenu) {
            fragmentManager.beginTransaction().replace(R.id.container, new MainMenuFragment()).commit();

        } else if (id == R.id.nav_module1) {
            fragmentManager.beginTransaction().replace(R.id.container, new Module1MenuFragment()).commit();

        } else if (id == R.id.nav_module2) {
            fragmentManager.beginTransaction().replace(R.id.container, new Module2MenuFragment()).commit();

        } else if (id == R.id.nav_module3) {
            fragmentManager.beginTransaction().replace(R.id.container, new Module3MenuFragment()).commit();

        } else if (id == R.id.nav_setting) {
            Intent i = new Intent(MenuActivity.this,UpdateAccountActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_logout) {
            Intent i = new Intent(MenuActivity.this,LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK );
            startActivity(i);
        }

        //Se cierra el menu desplegable
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
