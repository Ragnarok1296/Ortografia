package com.ortografia.trinidad.controllers.menus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ortografia.trinidad.R;

//Fragmento para el modulo 1 Diferencias
public class Module2MenuFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_module2_menu, container, false);
    }

}
