package com.example.duska.food;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity  {


    Button btn_help, btn_show, btn_addNew, btn_Settings;

    private Button btn_readLog, btn_deleteAll;
    DBHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /*btn_help = (Button) findViewById(R.id.btn_help);
        btn_addNew = (Button) findViewById(R.id.btn_addNew);
        btn_show = (Button) findViewById(R.id.btn_show);
        btn_Settings = (Button) findViewById(R.id.btn_Settings);

        btn_addNew.setOnClickListener(OncAll);
        btn_show.setOnClickListener(OncAll);
        btn_help.setOnClickListener(OncAll);
        btn_Settings.setOnClickListener(OncAll);*/

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        //TabItem tab1 = (TabItem) findViewById(R.id.tabItem_home);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_menu_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_add);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_settings);



    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new HomeFragment(), "Home");
        adapter.addFragment(new MainFragment(), "Add");
        adapter.addFragment(new SettingsFragment(), "Settings");
        viewPager.setAdapter(adapter);
    }




   /* View.OnClickListener OncAll = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case (R.id.btn_show):
                    Intent gotoshow = new Intent();
                    gotoshow.setClass(HomeActivity.this, ShowActivity.class);
                    startActivity(gotoshow);
                break;

                case (R.id.btn_help):
                    Intent gotohelp = new Intent();
                    gotohelp.setClass(HomeActivity.this, HelpActivity.class);
                    startActivity(gotohelp);
                    break;

                case (R.id.btn_addNew):
                    Intent gotoadd = new Intent();
                    gotoadd.setClass(HomeActivity.this, MainActivity.class);
                    startActivity(gotoadd);

                    break;

                case (R.id.btn_Settings):
                    Intent gotosettings = new Intent();
                    gotosettings.setClass(HomeActivity.this, SettingsActivity.class);
                    startActivity(gotosettings);
            }

        }
    };*/
}
