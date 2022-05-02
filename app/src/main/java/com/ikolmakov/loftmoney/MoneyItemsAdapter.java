package com.ikolmakov.loftmoney;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

// Adapter - отвечает за наполнение списка данными
public class MoneyItemsAdapter extends RecyclerView.Adapter<MoneyItemsAdapter.MoneyViewHolder> {

    private List<MoneyItem> moneyItemList = new ArrayList<>();

    public MoneyItemAdapterClick moneyItemAdapterClick;

    public void setData(List<MoneyItem> moneyItems) {
        moneyItemList.clear();
        moneyItemList.addAll(moneyItems);
        // Уведомляем адаптер что данные изменились
        notifyDataSetChanged();
    }

    public void setMoneyItemAdapterClick(MoneyItemAdapterClick moneyItemAdapterClick) {
        this.moneyItemAdapterClick = moneyItemAdapterClick;
    }

    // OnCreateViewHolder с помощью этого метода создаётся View ячейка
    @NonNull
    @Override
    public MoneyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View itemView = View.inflate(parent.getContext(), R.layout.cell_money, null);
//        return new MoneyViewHolder(itemView);

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        return new MoneyViewHolder(layoutInflater.inflate(R.layout.cell_money, parent, false), moneyItemAdapterClick);
    }

    // OnBindViewHolder с помощью этого метода View ячейка заполняется данными
    @Override
    public void onBindViewHolder(@NonNull MoneyViewHolder holder, int position) {
      holder.bindItem(moneyItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return moneyItemList.size();
    }
    // ViewHolder - отвечает за  обработку View ячейки списка
    static class MoneyViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView valueTextView;
        private MoneyItemAdapterClick moneyItemAdapterClick;

        public MoneyViewHolder(@NonNull View itemView, MoneyItemAdapterClick moneyItemAdapterClick) {
            super(itemView);

            this.moneyItemAdapterClick = moneyItemAdapterClick;
            titleTextView = itemView.findViewById(R.id.tv_title);
            valueTextView = itemView.findViewById(R.id.tv_amount);
        }

        public void bindItem(@NonNull final MoneyItem item) {
            titleTextView.setText(item.getTitle());
            valueTextView.setText(item.getValue()); // String.valueOf(moneyItem.getValue())

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (moneyItemAdapterClick != null) {
                        moneyItemAdapterClick.onItemClick(item);
                    }
                }
            });
        }

    }
}
