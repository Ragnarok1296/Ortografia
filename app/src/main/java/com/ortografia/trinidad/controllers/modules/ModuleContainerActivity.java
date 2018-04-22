package com.ortografia.trinidad.controllers.modules;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.ortografia.trinidad.R;
import com.ortografia.trinidad.controllers.modules.module1.lesson1.*;

public class ModuleContainerActivity extends AppCompatActivity {

    private ViewPager viewPager;
    //private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_container);

        viewPager = (ViewPager) findViewById(R.id.container);
        //tabLayout = (TabLayout) findViewById(R.id.vpSlider);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());



        //Se aniaden los fragments
        adapter.AddFragment(new Lesson1Page1Module1Fragment(), "Page1");
        adapter.AddFragment(new Lesson1Page2Module1Fragment(), "Page2");
        adapter.AddFragment(new Lesson1Page3Module1Fragment(), "Page3");
        adapter.AddFragment(new Lesson1Page4Module1Fragment(), "Page4");
        adapter.AddFragment(new Lesson1Page5Module1Fragment(), "Page5");
        adapter.AddFragment(new Lesson1Page6Module1Fragment(), "Page6");

        //se carga los fragments
        viewPager.setAdapter(adapter);
        //tabLayout.setupWithViewPager(viewPager);


    }
}
