package com.ortografia.trinidad.controllers.modules.module1.quiz;

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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ortografia.trinidad.R;
import com.ortografia.trinidad.models.ConecctionSQLiteHelper;
import com.ortografia.trinidad.models.Utilities;

public class QuizPage5Module1Fragment extends Fragment {

    //Generar los elementos del activity
    View view;
    TextView txtTitle;
    TextView txtContent;
    RadioButton rbOption1;
    RadioButton rbOption2;
    RadioButton rbOption3;
    RadioButton rbOption4;
    RadioButton rbOption5;
    Button btnCheck;
    ViewPager viewPager;

    //Creacion del objeto conexion
    ConecctionSQLiteHelper conn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_quiz_page5_module1, container, false);

        txtTitle = (TextView) view.findViewById(R.id.txtTitle);
        txtContent = (TextView) view.findViewById(R.id.txtContent);
        btnCheck = (Button) view.findViewById(R.id.btnCheck);
        rbOption1 = (RadioButton) view.findViewById(R.id.rbOption1);
        rbOption2 = (RadioButton) view.findViewById(R.id.rbOption2);
        rbOption3 = (RadioButton) view.findViewById(R.id.rbOption3);
        rbOption4 = (RadioButton) view.findViewById(R.id.rbOption4);
        rbOption5 = (RadioButton) view.findViewById(R.id.rbOption5);

        viewPager = (ViewPager) getActivity().findViewById(R.id.container);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkAnswer())
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
        String[] parameters = {"Acentuación","5"};
        String where = Utilities.FIELD_MODULE_QUIZ + "=? AND " + Utilities.FIELD_VERSION_QUIZ + "=?";
        //Se indican los campos que se quieren obtener
        String[] fields = {Utilities.FIELD_CONTENT_QUIZ};

        try{
            //Se ejecuta el query y se guarda en un cursosr
            Cursor cursor = db.query(Utilities.TABLE_QUIZZES,fields,where,parameters,null,null,null);


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

            txtTitle.setText(R.string.txtExcersice);
            txtTitle.setText(txtTitle.getText() + " 5");
            txtContent.setText(lesson);
            rbOption1.setText(option1);
            rbOption2.setText(option2);
            rbOption3.setText(option3);
            rbOption4.setText(option4);
            rbOption5.setText(option5);



            db.close();

        }catch (Exception e){

            Toast.makeText(getContext(),R.string.errorApplicattion,Toast.LENGTH_SHORT).show();

        }

    }

    private boolean checkAnswer(){
        if(rbOption4.isChecked())
            return true;
        else
            return false;
    }

    public void alertAnswerIncorrect() {

        new AlertDialog.Builder(getContext())
                .setTitle(R.string.title_incorrect)
                .setMessage(R.string.answer_incorrect_quiz)
                .setIcon(R.drawable.incorrect)
                .setPositiveButton(R.string.positive_button_ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                onBackPressed();
                                dialog.cancel();
                            }
                        })
                .show();

    }

    public void lessonFinish() {

        new AlertDialog.Builder(getContext())
                .setTitle(R.string.txtCongratulations)
                .setMessage(R.string.quiz_finished)
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
