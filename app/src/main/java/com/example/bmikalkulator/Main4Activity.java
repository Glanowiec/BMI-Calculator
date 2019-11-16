package com.example.bmikalkulator;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Main4Activity extends AppCompatActivity {

    //Shared preferences addition
    private static final String MEMORY = "Memory";
    private static final String NAME = "Name";
    private static final String HEIGHT = "Height";
    private static final String WEIGHT = "Weight";
    private static final String BMI = "BMI";
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        sharedPreferences = getSharedPreferences(MEMORY, Activity.MODE_PRIVATE);

        TextView nameView = findViewById(R.id.nameView);
        TextView heightView = findViewById(R.id.heightView);
        TextView weightView = findViewById(R.id.weightView);
        TextView bmiView = findViewById(R.id.bmiView);

        nameView.setText(sharedPreferences.getString(NAME, ""));
        heightView.setText(sharedPreferences.getString(HEIGHT, ""));
        weightView.setText(sharedPreferences.getString(WEIGHT, ""));
        bmiView.setText(sharedPreferences.getString(BMI, ""));
    }
}
