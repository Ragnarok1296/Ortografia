package com.ortografia.trinidad.controllers.modules;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ortografia.trinidad.R;
import com.ortografia.trinidad.controllers.modules.module1.lesson1.*;
import com.ortografia.trinidad.models.ViewPagerAdapter;

public class ModuleContainerActivity extends AppCompatActivity {

    private ViewPager viewPager;
    //private TabLayout tabLayout;
    ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_container);
        //Se inicia la inmersion completa
        hideBar();

        //Se manda llamar los datos de la leccion que se cargara
        Bundle parametros = getIntent().getExtras();
        String module = parametros.getString("Module");
        String lesson = parametros.getString("Lesson");

        viewPager = (ViewPager) findViewById(R.id.container);
        //tabLayout = (TabLayout) findViewById(R.id.vpSlider);

        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        fragments(module,lesson);

        //se carga los fragments
        viewPager.setAdapter(adapter);
        //tabLayout.setupWithViewPager(viewPager);


    }

    //Oculta las barras para la inmersion completa
    public void hideBar(){
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    private void fragments(String module, String lesson){

        switch (module){
            case "Module_1":

                switch (lesson){

                    case "Lesson_1":
                        //Se aniaden los fragments
                        adapter.AddFragment(new Lesson1Page1Module1Fragment(), "Page1");
                        adapter.AddFragment(new Lesson1Page2Module1Fragment(), "Page2");
                        adapter.AddFragment(new Lesson1Page3Module1Fragment(), "Page3");
                        adapter.AddFragment(new Lesson1Page4Module1Fragment(), "Page4");
                        adapter.AddFragment(new Lesson1Page5Module1Fragment(), "Page5");
                        adapter.AddFragment(new Lesson1Page6Module1Fragment(), "Page6");
                        break;

                }

                break;
        }

    }

}
