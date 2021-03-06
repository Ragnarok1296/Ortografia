package com.ortografia.trinidad.controllers.menus;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ortografia.trinidad.R;
import com.ortografia.trinidad.controllers.modules.ModuleContainerActivity;

//Fragmento para el modulo 1 Acentuacion
public class Module1MenuFragment extends Fragment  {

    //Generar los elementos del activity
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Cambiar el titulo del actionbar
        getActivity().setTitle(R.string.txtModule1);

        //Almacenar la view del fragment
        view = inflater.inflate(R.layout.fragment_module1_menu, container, false);

        RelativeLayout rlLesson1 = (RelativeLayout) view.findViewById(R.id.rlLesson1);
        RelativeLayout rlLesson2 = (RelativeLayout) view.findViewById(R.id.rlLesson2);
        RelativeLayout rlQuizz = (RelativeLayout) view.findViewById(R.id.rlQuizz);

        //Hace focus en el menu
        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(1).setChecked(true);

        TextView noLesson1 = (TextView) view.findViewById(R.id.txtLesson1_isComplete);
        TextView noLesson2 = (TextView) view.findViewById(R.id.txtLesson2_isComplete);
        TextView noQuiz = (TextView) view.findViewById(R.id.txtQuizz_isComplete);

        noLesson1.setText(noLesson1.getText()+" 3");
        noLesson2.setText(noLesson2.getText()+" 0");
        noQuiz.setText(noQuiz.getText()+" 5");

        rlLesson1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ModuleContainerActivity.class);
                intent.putExtra("Module","Module_1");
                intent.putExtra("Lesson","Lesson_1");
                startActivity(intent);
            }
        });

        rlLesson2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), R.string.txtcoming_soon, Toast.LENGTH_SHORT).show();
            }
        });

        rlQuizz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ModuleContainerActivity.class);
                intent.putExtra("Module","Module_1");
                intent.putExtra("Lesson","Quiz");
                startActivity(intent);
            }
        });

        return view;
    }




}
