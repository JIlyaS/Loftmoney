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
    }
}