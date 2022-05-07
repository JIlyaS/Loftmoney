package com.ikolmakov.loftmoney;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.ikolmakov.loftmoney.AddItemActivity.KEY_AMOUNT;

public class BudgetFragment extends Fragment  {
    public final static String tag = "budgetFragment";
    private static final int ADD_ITEM_REQUEST_CODE = 100;
    private RecyclerView itemsView;
    private MoneyItemsAdapter moneyItemsAdapter = new MoneyItemsAdapter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void generateMoney() {
        List<MoneyItem> moneyItems = new ArrayList<>();
        moneyItems.add(new MoneyItem("PS5", "30000"));
        moneyItems.add(new MoneyItem("Salary", "300000"));
        moneyItemsAdapter.setData(moneyItems);
    }

    // Загружаем Layout
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_budget, container, false);
        itemsView = view.findViewById(R.id.money_list);
        itemsView.setAdapter(moneyItemsAdapter);

        itemsView.addItemDecoration(new SimpleDividerItemDecorator(getActivity().getApplicationContext()));

        moneyItemsAdapter.moneyItemAdapterClick = new MoneyItemAdapterClick() {
            @Override
            public void onItemClick(MoneyItem moneyItem) {
                Toast.makeText(getActivity().getApplicationContext(), "Item clicked " + moneyItem.getValue(), Toast.LENGTH_LONG).show();
            }
        };

        // LayoutManager отвечает за расположение элементов в списке
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        itemsView.setLayoutManager(layoutManager);

        FloatingActionButton addButton = view.findViewById(R.id.add_button);
        addButton.setOnClickListener(v -> startActivityForResult(
                new Intent(getActivity().getApplicationContext(), AddItemActivity.class), ADD_ITEM_REQUEST_CODE
        ));

        generateMoney();

        return view;
    }

    // Проводим инициализацию, когда Layout уже загружен
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_ITEM_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                moneyItemsAdapter.addItem(
                        new MoneyItem(
                                data.getStringExtra(AddItemActivity.KEY_NAME),
                                data.getStringExtra(KEY_AMOUNT)
                        )
                );
            }
        }
    }
}
