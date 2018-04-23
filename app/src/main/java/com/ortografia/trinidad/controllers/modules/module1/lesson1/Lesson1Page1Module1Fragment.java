package com.ortografia.trinidad.controllers.modules.module1.lesson1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ortografia.trinidad.R;
import com.ortografia.trinidad.models.ConecctionSQLiteHelper;
import com.ortografia.trinidad.models.Utilities;

public class Lesson1Page1Module1Fragment extends Fragment {

    //Generar los elementos del activity
    View view;
    TextView txtTitle;
    TextView txtContent;
    TextView txtExample;
    Button btnNext;
    ViewPager viewPager;

    //Creacion del objeto conexion
    ConecctionSQLiteHelper conn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_lesson1_page1_module1, container, false);

        txtTitle = (TextView) view.findViewById(R.id.txtTitle);
        txtContent = (TextView) view.findViewById(R.id.txtContent);
        txtExample = (TextView) view.findViewById(R.id.txtExample);
        btnNext = (Button) view.findViewById(R.id.btnNext);

        viewPager = (ViewPager) getActivity().findViewById(R.id.container);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(getItem(+1), true); //getItem(-1) for previous
            }
        });

        //Instanciacion de la conexion
        conn = new ConecctionSQLiteHelper(getContext(), Utilities.DATABASE, null, 1);

        content();

        return view;
    }

    private int getItem(int i) { return viewPager.getCurrentItem() + i; }

    private void content() {
        //Se inicia la base de datos
        SQLiteDatabase db = conn.getReadableDatabase();

        //Se selecciona el(los) parametros a utilizar en el where
        String[] parameters = {"Acentuación","Introducción","1"};
        String where = Utilities.FIELD_MODULE + "=? AND " + Utilities.FIELD_TITLE + "=? AND " + Utilities.FIELD_VERSION + "=?";
        //Se indican los campos que se quieren obtener
        String[] fields = {Utilities.FIELD_TITLE, Utilities.FIELD_CONTENT};

        try{
            //Se ejecuta el query y se guarda en un cursosr
            Cursor cursor = db.query(Utilities.TABLE_LESSONS,fields,where,parameters,null,null,null);


            //Se obtienen los datos arrojados
            cursor.moveToFirst();
            String title = cursor.getString(0);
            String content = cursor.getString(1);

            String[] split = content.split("@");
            String lesson = split[0];
            String example = split[1];

            txtTitle.setText(title+"\nParte 1");
            txtContent.setText(lesson);
            txtExample.setText(example);

            db.close();

        }catch (Exception e){

            Toast.makeText(getContext(),R.string.errorApplicattion,Toast.LENGTH_SHORT).show();

        }

    }


}
