package com.ikolmakov.loftmoney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

        configureRecyclerView();

        generateMoney();

        FloatingActionButton addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(v -> startActivityForResult(
                new Intent(MainActivity.this, AddItemActivity.class), ADD_ITEM_REQUEST_CODE
        ));
    }

    private void generateMoney() {
      List<MoneyItem> moneyItems = new ArrayList<>();
      moneyItems.add(new MoneyItem("PS5", "30000"));
      moneyItems.add(new MoneyItem("Salary", "300000"));
      moneyItemsAdapter.setData(moneyItems);
    }

    private void configureRecyclerView() {
      itemsView = findViewById(R.id.money_list);
      itemsView.setAdapter(moneyItemsAdapter);

      moneyItemsAdapter.moneyItemAdapterClick = new MoneyItemAdapterClick() {
          @Override
          public void onItemClick(MoneyItem moneyItem) {
              Toast.makeText(getApplicationContext(), "Item clicked " + moneyItem.getValue(), Toast.LENGTH_LONG).show();
          }
      };
      // LayoutManager отвечает за расположение элементов в списке
      RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
      itemsView.setLayoutManager(layoutManager);
    }
}