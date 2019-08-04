package com.newspaper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class SlidePageAdapter extends FragmentStatePagerAdapter
{
    private List<Fragment> fragmentList;
    public SlidePageAdapter(FragmentManager fe,List<Fragment> fragmentList)
    {
        super(fe);
        this.fragmentList=fragmentList;
    }
    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
