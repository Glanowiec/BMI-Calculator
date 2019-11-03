package com.example.bmikalkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.PublicKey;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button author;
    private Button search;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        author = (Button) findViewById(R.id.calculateBMI);
        author.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        EditText heightField = findViewById(R.id.heightField);
        EditText weightField = findViewById(R.id.weightField);
        TextView resultField = findViewById(R.id.resultField);

        double height = Double.parseDouble(heightField.getText().toString());
        double weight = Double.parseDouble(weightField.getText().toString());

        double BMI = weight/(height * height)*10000;

        if(BMI>25){

            resultField.setText("Nadwaga, Twoje BMI:"+ Double.toString((int)BMI));

        }
        else if(BMI>20){

            resultField.setText("PrawidlowawagaTwoje BMI:"+ Double.toString((int)BMI));

        }
        else {

            resultField.setText("NiedowagaTwoje BMI: "+ Double.toString((int)BMI));

        }
    }
}
