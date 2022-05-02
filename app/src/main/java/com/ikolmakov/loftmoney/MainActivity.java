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

//    private static final String KEY_AMOUNT = "amount";
//    private static final String KEY_NAME = "name";

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

//        Intent intent = new Intent(this, AddItemActivity.class);
//        startActivity(intent);

    }

    private void generateMoney() {
      List<MoneyItem> moneyItems = new ArrayList<>();
      moneyItems.add(new MoneyItem("PS5", "30000"));
      moneyItems.add(new MoneyItem("Salary", "300000"));
      moneyItemsAdapter.setData(moneyItems);
    }

//    @Override
//    protected  void onActivityrsult(final int requestCode, final int resultCode, @Nullable final Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == ADD_ITEM_REQUEST_CODE && resultCode == RESULT_OK) {
//            if (data != null) {
//                moneyItemsAdapter.add(new MoneyItem(data.getStringExtra(KEY_NAME), Integer.parseInt(data.getStringExtra(KEY_AMOUNT))));
//            }
//        }
//    }

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