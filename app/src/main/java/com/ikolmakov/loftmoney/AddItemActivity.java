package com.ikolmakov.loftmoney;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class AddItemActivity extends AppCompatActivity {

    private EditText textExpense;
    private EditText textName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        Button addButton = findViewById(R.id.add_button);
        textExpense = findViewById(R.id.expense);
        textName = findViewById(R.id.name);

        setTextWatcher(textExpense, addButton);
        setTextWatcher(textName, addButton);
    }

    private void setTextWatcher(EditText editText, Button addButton) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!textExpense.getText().toString().isEmpty() && !textName.getText().toString().isEmpty()) {
                    addButton.setEnabled(true);
                } else {
                    addButton.setEnabled(false);
                }
            }
        });
    }
}
