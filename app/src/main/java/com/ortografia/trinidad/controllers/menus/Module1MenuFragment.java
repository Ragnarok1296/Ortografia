package com.ortografia.trinidad.controllers.menus;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ortografia.trinidad.R;

//Fragmento para el modulo 1 Acentuacion
public class Module1MenuFragment extends Fragment {

    //Generar los elementos del activity
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Cambiar el titulo del actionbar
        getActivity().setTitle(R.string.txtModule1);

        //Almacenar la view del fragment
        view = inflater.inflate(R.layout.fragment_module1_menu, container, false);

        //Hace focus en el menu
        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(1).setChecked(true);

        return view;
    }

}
