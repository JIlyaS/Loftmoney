package com.ikolmakov.loftmoney;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class AddItemActivity extends AppCompatActivity {

    private EditText textAmount;
    private EditText textName;
    public static final String KEY_AMOUNT = "amount";
    public static final String KEY_NAME = "name";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        Button addButton = findViewById(R.id.add_button);
        textAmount = findViewById(R.id.expense);
        textName = findViewById(R.id.name);

        setTextWatcher(textAmount, addButton);
        setTextWatcher(textName, addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                String name = textName.getText().toString();
                String price = textAmount.getText().toString();

                Intent intent = new Intent();
                intent.putExtra(KEY_NAME, name);
                intent.putExtra(KEY_AMOUNT, price);

                setResult(RESULT_OK, intent);

                finish();
            }
        });
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
                if (!textAmount.getText().toString().isEmpty() && !textName.getText().toString().isEmpty()) {
                    addButton.setEnabled(true);
                } else {
                    addButton.setEnabled(false);
                }
            }
        });
    }
}
