package com.itsniv.attendenceapplication.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.itsniv.attendenceapplication.R;
import com.itsniv.attendenceapplication.fragments.FragmentTwo;
import com.itsniv.attendenceapplication.fragments.OneFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by it's niv on 29-11-2016.
 */

public class Menu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        init();
    }

    private void init() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout_menu);

        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {

        SimplePagerAdapter simplePagerAdapter = new SimplePagerAdapter(getSupportFragmentManager());
        simplePagerAdapter.addFragment(new OneFragment(),"Summary");
        simplePagerAdapter.addFragment(new FragmentTwo(),"Feedback");

        viewPager.setAdapter(simplePagerAdapter);

    }

    private class SimplePagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragmetList = new ArrayList<>();
        private List<String> fragmentTitleList = new ArrayList<>();


        public SimplePagerAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmetList.get(position);
        }

        @Override
        public int getCount()    {
            return fragmetList.size();
        }

        public void addFragment(Fragment oneFragment, String one) {
            fragmetList.add(oneFragment);
            fragmentTitleList.add(one);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);
        }
    }
}
