package com.ortografia.trinidad.controllers.modules.module3.lesson1;

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

public class Lesson1Page6Module3Fragment extends Fragment {

    //Generar los elementos del activity
    View view;
    TextView txtTitle;
    TextView txtContent;
    CheckBox cbOption1;
    CheckBox cbOption2;
    CheckBox cbOption3;
    CheckBox cbOption4;
    Button btnCheck;
    ViewPager viewPager;

    //Creacion del objeto conexion
    ConecctionSQLiteHelper conn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_lesson1_page6_module3, container, false);

        txtTitle = (TextView) view.findViewById(R.id.txtTitle);
        txtContent = (TextView) view.findViewById(R.id.txtContent);
        btnCheck = (Button) view.findViewById(R.id.btnCheck);
        cbOption1 = (CheckBox) view.findViewById(R.id.cbOption1);
        cbOption2 = (CheckBox) view.findViewById(R.id.cbOption2);
        cbOption3 = (CheckBox) view.findViewById(R.id.cbOption3);
        cbOption4 = (CheckBox) view.findViewById(R.id.cbOption4);

        viewPager = (ViewPager) getActivity().findViewById(R.id.container);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkAnswer())
                    lessonFinish();
                else
                    alertAnswerIncorrect();

            }
        });

        //Instanciacion de la conexion
        conn = new ConecctionSQLiteHelper(getContext(), Utilities.DATABASE, null, 1);

        content();
        return view;

    }

    public void onBackPressed() {
        super.getActivity().onBackPressed();
    }

    private int getItem(int i) { return viewPager.getCurrentItem() + i; }

    private void content() {
        //Se inicia la base de datos
        SQLiteDatabase db = conn.getReadableDatabase();

        //Se selecciona el(los) parametros a utilizar en el where
        String[] parameters = {"8","1"};
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

            txtTitle.setText(R.string.txtExcersice);
            txtContent.setText(lesson);
            cbOption1.setText(option1);
            cbOption2.setText(option2);
            cbOption3.setText(option3);
            cbOption4.setText(option4);


            db.close();

        }catch (Exception e){

            Toast.makeText(getContext(),R.string.errorApplicattion,Toast.LENGTH_SHORT).show();

        }

    }

    private boolean checkAnswer(){
        if(cbOption1.isChecked() && !cbOption2.isChecked() && cbOption3.isChecked() && !cbOption4.isChecked())
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

    public void lessonFinish() {

        new AlertDialog.Builder(getContext())
                .setTitle(R.string.txtCongratulations)
                .setMessage(R.string.lesson_finished)
                .setIcon(R.drawable.correct)
                .setPositiveButton(R.string.positive_button_ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                onBackPressed();
                                dialog.cancel();
                            }
                        })
                .show();

    }

}
