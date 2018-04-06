package com.ortografia.trinidad.controllers.menus;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ortografia.trinidad.R;
import com.ortografia.trinidad.models.User;


public class MainMenuFragment extends Fragment {

    //Generar los elementos del activity
    View view;
    ImageButton ibModule1;
    ImageButton ibModule2;
    ImageButton ibModule3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Cambiar el titulo del actionbar
        getActivity().setTitle(R.string.txtMainMenu);

        //Almacenar la view del fragment
        view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        //Traer elementos del layout
        ibModule1 = (ImageButton) view.findViewById(R.id.ibModule1);
        ibModule2 = (ImageButton) view.findViewById(R.id.ibModule2);
        ibModule3 = (ImageButton) view.findViewById(R.id.ibModule3);

        //Hace focus en el menu
        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);

        //Se carga el nombre del usuario en el texto para darle la bienvenida
        TextView txtUser = (TextView) view.findViewById(R.id.txtUserFragmentMainMenu);
        txtUser.setText(User.getName().toString() + " " + User.getLastName().toString());

        //Ir al modulo 1
        ibModule1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, new Module1MenuFragment());
                transaction.commit();

            }
        });

        //Ir al modulo 2
        ibModule2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, new Module2MenuFragment());
                transaction.commit();
            }
        });

        //Ir al modulo 3
        ibModule3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, new Module3MenuFragment());
                transaction.commit();
            }
        });

        return view;

    }

}
