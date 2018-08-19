package com.ortografia.trinidad.controllers.modules.module2.lesson1;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.ortografia.trinidad.R;
import com.ortografia.trinidad.models.ConecctionSQLiteHelper;
import com.ortografia.trinidad.models.Utilities;

public class Lesson1Page4Module2Fragment extends Fragment {

    //Generar los elementos del activity
    View view;
    TextView txtTitle;
    TextView txtContent;
    CheckBox cbOption1;
    CheckBox cbOption2;
    CheckBox cbOption3;
    CheckBox cbOption4;
    CheckBox cbOption5;
    CheckBox cbOption6;
    CheckBox cbOption7;
    CheckBox cbOption8;
    Button btnCheck;
    ViewPager viewPager;

    //Creacion del objeto conexion
    ConecctionSQLiteHelper conn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_lesson1_page4_module2, container, false);

        txtTitle = (TextView) view.findViewById(R.id.txtTitle);
        txtContent = (TextView) view.findViewById(R.id.txtContent);
        btnCheck = (Button) view.findViewById(R.id.btnCheck);
        cbOption1 = (CheckBox) view.findViewById(R.id.cbOption1);
        cbOption2 = (CheckBox) view.findViewById(R.id.cbOption2);
        cbOption3 = (CheckBox) view.findViewById(R.id.cbOption3);
        cbOption4 = (CheckBox) view.findViewById(R.id.cbOption4);
        cbOption5 = (CheckBox) view.findViewById(R.id.cbOption5);
        cbOption6 = (CheckBox) view.findViewById(R.id.cbOption6);
        cbOption7 = (CheckBox) view.findViewById(R.id.cbOption7);
        cbOption8 = (CheckBox) view.findViewById(R.id.cbOption8);

        viewPager = (ViewPager) getActivity().findViewById(R.id.container);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkAnswer())
                    viewPager.setCurrentItem(getItem(+1), true);
                else
                    alertAnswerIncorrect();
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
        String[] parameters = {"4","1"};
        String where = Utilities.FIELD_IDLESSON + "=? AND " + Utilities.FIELD_VERSIONLESSON + "=?";
        //Se indican los campos que se quieren obtener
        String[] fields = {Utilities.FIELD_CONTENT_EXAMPLES};

        try{
            //Se ejecuta el query y se guarda en un cursosr
            Cursor cursor = db.query(Utilities.TABLE_EXAMPLES,fields,where,parameters,null,null,null);


            //Se obtienen los datos arrojados
            cursor.moveToFirst();
            String content = cursor.getString(0);

            String[] split = content.split("@");
            String lesson = split[0];
            String option1 = split[1];
            String option2 = split[2];
            String option3 = split[3];
            String option4 = split[4];
            String option5 = split[5];
            String option6 = split[6];
            String option7 = split[7];
            String option8 = split[8];

            txtTitle.setText(R.string.txtExcersice);
            txtContent.setText(lesson);
            cbOption1.setText(option1);
            cbOption2.setText(option2);
            cbOption3.setText(option3);
            cbOption4.setText(option4);
            cbOption5.setText(option5);
            cbOption6.setText(option6);
            cbOption7.setText(option7);
            cbOption8.setText(option8);


            db.close();

        }catch (Exception e){

            Toast.makeText(getContext(),R.string.errorApplicattion,Toast.LENGTH_SHORT).show();

        }

    }

    private boolean checkAnswer(){
        if(cbOption1.isChecked() && !cbOption2.isChecked() && cbOption3.isChecked() && !cbOption4.isChecked()
            && cbOption5.isChecked() && !cbOption6.isChecked() && !cbOption7.isChecked() && cbOption8.isChecked())
            return true;
        else
            return false;
    }

    public void alertAnswerIncorrect() {

        new AlertDialog.Builder(getContext())
                .setTitle(R.string.title_incorrect)
                .setMessage(R.string.answer_incorrect)
                .setIcon(R.drawable.incorrect)
                .setPositiveButton(R.string.positive_button_ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                viewPager.setCurrentItem(getItem(-1), true);


                                dialog.cancel();
                            }
                        })
                .show();

    }


}
