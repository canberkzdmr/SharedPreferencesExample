package com.example.sharedpreferencessample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etNumber1;
    EditText etNumber2;
    TextView tvResult;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumber1 = findViewById(R.id.etNumber1);
        etNumber2 = findViewById(R.id.etNumber2);
        tvResult = findViewById(R.id.tvResult);

        sharedPreferences = this.getSharedPreferences("com.example.sharedpreferencessample", Context.MODE_PRIVATE);
        int number1 = sharedPreferences.getInt("number1", 0);
        int number2 = sharedPreferences.getInt("number2", 0);
        int result = sharedPreferences.getInt("result", 0);

        etNumber1.setText(String.valueOf(number1));
        etNumber2.setText(String.valueOf(number2));
        tvResult.setText(String.valueOf("Result: " + result));

        if (number1 == 0 || number2 == 0) {
            etNumber1.setText("");
            etNumber2.setText("");
            tvResult.setText(tvResult.getHint().toString());
        }
    }

    public void saveSharedPreferenceNumbers(int number1, int number2, int result) {
        sharedPreferences.edit().putInt("number1", number1).apply();
        sharedPreferences.edit().putInt("number2", number2).apply();
        sharedPreferences.edit().putInt("result", result).apply();
    }

    public void sum(View view) {
        if (etNumber1.getText().toString().matches("") || etNumber2.getText().toString().matches("")) {
            tvResult.setText("Please enter a valid number!");
        } else {
            int number1 = Integer.parseInt(etNumber1.getText().toString());
            int number2 = Integer.parseInt(etNumber2.getText().toString());
            int result = number1 + number2;

            tvResult.setText("Result: " + String.valueOf(result));

            showAlert(number1, number2, result);
        }

    }

    public void sub(View view) {

        if (etNumber1.getText().toString().matches("") || etNumber2.getText().toString().matches("")) {
            tvResult.setText("Please enter a valid number!");
        } else {
            int number1 = Integer.parseInt(etNumber1.getText().toString());
            int number2 = Integer.parseInt(etNumber2.getText().toString());
            int result = number1 - number2;

            tvResult.setText("Result: " + String.valueOf(result));

            showAlert(number1, number2, result);
        }

    }

    public void multiply(View view) {
        if (etNumber1.getText().toString().matches("") || etNumber2.getText().toString().matches("")) {
            tvResult.setText("Please enter a valid number!");
        } else {
            int number1 = Integer.parseInt(etNumber1.getText().toString());
            int number2 = Integer.parseInt(etNumber2.getText().toString());
            int result = number1 * number2;

            tvResult.setText("Result: " + String.valueOf(result));

            showAlert(number1, number2, result);
        }
    }

    public void divide(View view) {
        if (etNumber1.getText().toString().matches("") || etNumber2.getText().toString().matches("")) {
            tvResult.setText("Please enter a valid number!");
        } else if (!etNumber1.getText().toString().matches("") && etNumber2.getText().toString().matches("0")) {
            Toast.makeText(MainActivity.this, "Divide 0 exception!", Toast.LENGTH_SHORT).show();
        } else {
            int number1 = Integer.parseInt(etNumber1.getText().toString());
            int number2 = Integer.parseInt(etNumber2.getText().toString());
            int result = number1 / number2;

            tvResult.setText("Result: " + String.valueOf(result));

            showAlert(number1, number2, result);
        }

    }

    public void showAlert(int number1, int number2, int result) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Hey!");
        alert.setMessage("Do you want to save your inputs?");
        alert.setPositiveButton("Yes!", (dialog, which) -> {
            saveSharedPreferenceNumbers(number1, number2, result);
            Toast.makeText(MainActivity.this, "Saved Successfully!", Toast.LENGTH_SHORT).show();
        });
        alert.setNegativeButton("No!", null);
        alert.show();
    }

}