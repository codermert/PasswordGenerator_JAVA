package com.example.passwordgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    final private Context CONTEXT = this;

    final private int MAX_LENGTH = 24, MIN_LENGTH = 2;

    private CheckBox check_capital_letters, check_small_letters, check_numbers, check_symbols;

    private TextView tv_password;
    private EditText et_password_length;

    private String[] capital_letters_array = new String[26], small_letters_array = new String[26], numbers_array = new String[10], symbols_array;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        check_capital_letters = findViewById(R.id.checkbox_capital_letters);
        check_small_letters = findViewById(R.id.checkbox_small_letters);
        check_numbers = findViewById(R.id.checkbox_numbers);
        check_symbols = findViewById(R.id.checkbox_symbols);

        tv_password = findViewById(R.id.tv_password);
        et_password_length = findViewById(R.id.et_password_length);

        //Initialize arrays
        for(int i = 0; i < 26; i++) capital_letters_array[i] = String.valueOf((char) (i+65));
        for(int i = 0; i < 26; i++) small_letters_array[i] = String.valueOf((char) (i+97));
        for(int i = 0; i < 10; i++) numbers_array[i] = String.valueOf(i);
        symbols_array = new String[]{"#", "&", "/", "-", "_", "+", "=", "%", "$", ";", "<", ">", "(", ")", "{", "}", "[", "]"};

    }

    //handle generate button click
    public void generate(View view) {

        if(!check_capital_letters.isChecked() && !check_small_letters.isChecked() && !check_numbers.isChecked() && !check_symbols.isChecked()) {
            //No choices selected
            Toast.makeText(this, "En az bir öğe seçin", Toast.LENGTH_LONG).show();
            return;
        }

        if(et_password_length.length() < 1) {
            et_password_length.setText(String.valueOf(MIN_LENGTH));
            Toast.makeText(this, "Uzunluk boş olmamalıdır", Toast.LENGTH_LONG).show();
            return;
        }

        int current_val = Integer.parseInt(et_password_length.getText().toString());

        if(current_val < MIN_LENGTH) {
            et_password_length.setText(String.valueOf(MIN_LENGTH));
            Toast.makeText(CONTEXT, "Minimum değer "+ MIN_LENGTH, Toast.LENGTH_LONG).show();
            return;
        }

        else if(current_val > MAX_LENGTH) {
            et_password_length.setText(String.valueOf(MAX_LENGTH));
            Toast.makeText(CONTEXT, "Maksimum değer "+ MAX_LENGTH, Toast.LENGTH_LONG).show();
            return;
        }

        boolean[] choices = {check_capital_letters.isChecked(), check_small_letters.isChecked(), check_numbers.isChecked(), check_symbols.isChecked()};
        String[][] elements = {capital_letters_array, small_letters_array, numbers_array, symbols_array};

        StringBuilder new_password = new StringBuilder();

        int password_length = Integer.parseInt(et_password_length.getText().toString());

        for(int i = 0; i < password_length; i++) {

            int random_choice;

            do {
                random_choice = (int) (Math.random() * choices.length);
                if(random_choice >= choices.length) random_choice = choices.length - 1;
            } while(!choices[random_choice]);

            int random_char_index = (int) (Math.random() * elements[random_choice].length - 1);
            if(random_char_index >= elements[random_choice].length) random_char_index = elements[random_choice].length - 1;

            new_password.append(elements[random_choice][random_char_index]);

        }

        tv_password.setText(new_password);
        Toast.makeText(this, "Random şifre oluşturuldu", Toast.LENGTH_SHORT).show();

    }

    public void copy(View view) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Random şifre", tv_password.getText().toString());
        Objects.requireNonNull(clipboard).setPrimaryClip(clip);
        Toast.makeText(this, "Random şifre kopyalandı", Toast.LENGTH_LONG).show();
    }
}
