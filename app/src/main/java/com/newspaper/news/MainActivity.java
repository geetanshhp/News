package com.newspaper.news;

import android.animation.ArgbEvaluator;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.newspaper.SlidePageAdapter;
import com.newspaper.fragements.enterainmentNews;
import com.newspaper.fragements.nationalNews;
import com.newspaper.fragements.otherNews;
import com.newspaper.fragements.sportsNews;
import com.newspaper.fragements.trandingNews;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    ViewPager viewPager;
    PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Fragment> list=new ArrayList<>();
        list.add(new trandingNews());
        list.add(new nationalNews());
        list.add(new sportsNews());
        list.add(new enterainmentNews());
        list.add(new otherNews());
        viewPager=findViewById(R.id.viewpager);
        pagerAdapter=new SlidePageAdapter(getSupportFragmentManager(),list);
        viewPager.setAdapter(pagerAdapter);
    }
    @Override
    public void onBackPressed()
    {
        final AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("ARE YOU SURE TO EXIT");
        alert.setIcon(R.drawable.sutraicon);
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                finish();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }

        });
        alert.show();

    }

}
