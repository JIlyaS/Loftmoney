package com.ikolmakov.loftmoney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int ADD_ITEM_REQUEST_CODE = 100;

    private RecyclerView itemsView;

    private MoneyItemsAdapter moneyItemsAdapter = new MoneyItemsAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabs = findViewById(R.id.tabs);
        ViewPager pages = findViewById(R.id.viewpager);
        BudgetFragment budgetFragment = new BudgetFragment();
        pages.setAdapter(new MainPagerAdapter(getSupportFragmentManager(), MainActivity.this));
        // заполняет текст вкладок и
        // делает так, чтобы активная вкладка автоматически менялась
        // при перелистывании.
        tabs.setupWithViewPager(pages);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        pages.addOnPageChangeListener(
                new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                        Здесь не могу прокинуть цвет дальше!
//                        if (position == 0) {
//                            Bundle bundle = new Bundle();
//                            bundle.putInt("color", R.color.success_color);
//                            budgetFragment.setArguments(bundle);
//                            fragmentTransaction.add(R.id.fragment_container_view, BudgetFragment.class, null).commit();
//                        } else if (position == 1) {
//                           Bundle bundle = new Bundle();
//                           bundle.putInt("color", R.color.success_color);
//                           budgetFragment.setArguments(bundle);
//                           fragmentTransaction.add(R.id.fragment_container_view, BudgetFragment.class, null).commit();
//                        }
                    }

                    @Override
                    public void onPageSelected(int position) {

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                }
        );
    }
}