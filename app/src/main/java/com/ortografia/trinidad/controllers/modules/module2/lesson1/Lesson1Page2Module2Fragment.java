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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ortografia.trinidad.R;
import com.ortografia.trinidad.models.ConecctionSQLiteHelper;
import com.ortografia.trinidad.models.Utilities;

public class Lesson1Page2Module2Fragment extends Fragment {

    //Generar los elementos del activity
    View view;
    TextView txtTitle;
    TextView txtContent;
    RadioButton rbOption1;
    RadioButton rbOption2;
    RadioButton rbOption3;
    RadioButton rbOption4;
    Button btnCheck;
    ViewPager viewPager;

    //Creacion del objeto conexion
    ConecctionSQLiteHelper conn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_lesson1_page2_module2, container, false);

        txtTitle = (TextView) view.findViewById(R.id.txtTitle);
        txtContent = (TextView) view.findViewById(R.id.txtContent);
        btnCheck = (Button) view.findViewById(R.id.btnCheck);
        rbOption1 = (RadioButton) view.findViewById(R.id.rbOption1);
        rbOption2 = (RadioButton) view.findViewById(R.id.rbOption2);
        rbOption3 = (RadioButton) view.findViewById(R.id.rbOption3);
        rbOption4 = (RadioButton) view.findViewById(R.id.rbOption4);

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
        String[] parameters = {"3","1"};
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
            rbOption1.setText(option1);
            rbOption2.setText(option2);
            rbOption3.setText(option3);
            rbOption4.setText(option4);


            db.close();

        }catch (Exception e){

            Toast.makeText(getContext(),R.string.errorApplicattion,Toast.LENGTH_SHORT).show();

        }

    }

    private boolean checkAnswer(){
        if(rbOption3.isChecked())
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
