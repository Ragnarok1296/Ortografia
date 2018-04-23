package com.ortografia.trinidad.controllers.modules.module1.lesson1;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ortografia.trinidad.R;
import com.ortografia.trinidad.models.ConecctionSQLiteHelper;
import com.ortografia.trinidad.models.Utilities;

public class Lesson1Page6Module1Fragment extends Fragment {

    //Generar los elementos del activity
    View view;
    TextView txtTitle;
    TextView txtContent;
    EditText etAnswer;
    Button btnCheck;
    ViewPager viewPager;

    //Creacion del objeto conexion
    ConecctionSQLiteHelper conn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_lesson1_page6_module1, container, false);

        txtTitle = (TextView) view.findViewById(R.id.txtTitle);
        txtContent = (TextView) view.findViewById(R.id.txtContent);
        btnCheck = (Button) view.findViewById(R.id.btnCheck);
        etAnswer = (EditText) view.findViewById(R.id.etAnswer);

        viewPager = (ViewPager) getActivity().findViewById(R.id.container);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etAnswer.getText().toString().equals("")) {
                    if (checkAnswer())
                        lessonFinish();
                    else
                        alertAnswerIncorrect();
                }
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
        String[] parameters = {"2","1"};
        String where = Utilities.FIELD_IDLESSON + "=? AND " + Utilities.FIELD_VERSIONLESSON + "=?";
        //Se indican los campos que se quieren obtener
        String[] fields = {Utilities.FIELD_CONTENT_EXAMPLES};

        try{
            //Se ejecuta el query y se guarda en un cursosr
            Cursor cursor = db.query(Utilities.TABLE_EXAMPLES,fields,where,parameters,null,null,null);


            //Se obtienen los datos arrojados
            cursor.moveToFirst();
            String content = cursor.getString(0);

            txtTitle.setText(R.string.txtExcersice);
            txtContent.setText(content);

            db.close();

        }catch (Exception e){

            Toast.makeText(getContext(),R.string.errorApplicattion,Toast.LENGTH_SHORT).show();

        }

    }

    private boolean checkAnswer(){
        if(etAnswer.getText().toString().equals("4"))
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
