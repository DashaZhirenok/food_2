package com.example.duska.food;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_menu_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_add);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_show);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_settings);
        tabLayout.getTabAt(4).setIcon(R.drawable.ic_help);


    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new HomeFragment(), "Home");
        adapter.addFragment(new MainFragment(), "Add");
        adapter.addFragment(new ShowFragment(), "Show");
        adapter.addFragment(new SettingsFragment(), "Settings");
        adapter.addFragment(new HelpFragment(), "Help");
        viewPager.setAdapter(adapter);
    }


}
