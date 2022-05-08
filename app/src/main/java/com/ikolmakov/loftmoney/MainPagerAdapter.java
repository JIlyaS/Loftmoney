package com.ikolmakov.loftmoney;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MainPagerAdapter extends FragmentPagerAdapter {

    private String[] titles = {};

    private final String[] types = {"expense", "income"};

    private Context context;

    MainPagerAdapter(FragmentManager fm, Context context) {
        super(fm); // getSupportFragmentManager()

        this.context = context;

        titles = context.getResources().getStringArray(R.array.main_pager_titles);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == getCount() - 1)
            return new BalanceFragment();
        Bundle args = new Bundle();
        args.putString("type", types[position]);
        if (position == 0) {
            args.putString("typeFragment", "expenses");
        } else if (position == 1) {
            args.putString("typeFragment", "income");
        }
        final BudgetFragment itemsFragment = new BudgetFragment();
        itemsFragment.setArguments(args);
        return itemsFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // С помощью этого TabLayout отобразит
        // нужное название на каждой вкладке
        return titles[position];
    }

    @Override
    public int getCount() {
        return titles.length;
    }
}
