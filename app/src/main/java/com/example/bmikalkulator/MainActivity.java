package com.example.bmikalkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button bmiCalculationBtn;
    private Button viewSavedBmiDataBtn;
    private Button viewBmiInfoBtn;
    private Button viewAuthorBtn;
    private Button searchBtn;

    //Shared preferences addition
    private static final String MEMORY = "Memory";
    private static final String NAME = "Name";
    private static final String HEIGHT = "Height";
    private static final String WEIGHT = "Weight";
    private static final String BMI = "BMI";
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(MEMORY, Activity.MODE_PRIVATE);

        //On click BMI calculation
        bmiCalculationBtn = (Button) findViewById(R.id.calculateBMI);
        bmiCalculationBtn.setOnClickListener(view -> calculateBmi(view));

        //On click - Open new activity where we place shared preferenced saved data
        viewSavedBmiDataBtn = (Button) findViewById(R.id.viewSavedBmiData);
        viewSavedBmiDataBtn.setOnClickListener(view -> viewSavedBmiData(view));

        //On click - Goes to BMI Wikipedia
        viewBmiInfoBtn = (Button) findViewById(R.id.viewBMIInfo);
        viewBmiInfoBtn.setOnClickListener(view -> viewBmiInfo(view));

        //On click - Open new Activity with WebView - In WebView we are using html file
        viewAuthorBtn = (Button) findViewById(R.id.viewAuthorInfo);
        viewAuthorBtn.setOnClickListener(view -> viewAuthorInfo());

        //On click - Open new Activity with WebView - In WebView we are using google url to search
        searchBtn = (Button) findViewById(R.id.search);
        searchBtn.setOnClickListener(view -> viewSearchResults());
    }


    public void calculateBmi(View view) {
        EditText nameField = findViewById(R.id.nameField);
        EditText heightField = findViewById(R.id.heightField);
        EditText weightField = findViewById(R.id.weightField);
        TextView resultField = findViewById(R.id.resultField);


        if (nameField.getText().toString().isEmpty() || heightField.getText().toString().isEmpty() || weightField.getText().toString().isEmpty()) {
            resultField.setTextColor(Color.RED);
            resultField.setText("Podaj wszystkie wartości");
        } else {
            String name = nameField.getText().toString();
            double height = Double.parseDouble(heightField.getText().toString());
            double weight = Double.parseDouble(weightField.getText().toString());

            double BMI = weight / (height * height) * 10000;

            resultField.setTextColor(Color.GRAY);

            if (BMI > 25) {

                resultField.setText("Nadwaga, Twoje BMI:" + Double.toString((int) BMI));

            } else if (BMI > 20) {

                resultField.setText("Prawidłowa waga, Twoje BMI:" + Double.toString((int) BMI));

            } else {

                resultField.setText("Niedowaga, Twoje BMI: " + Double.toString((int) BMI));

            }
            saveBmiData(name, Double.toString((int) height), Double.toString((int) weight), Double.toString((int) BMI));
        }
    }

    public void viewSavedBmiData(View view){
        Intent viewSavedBmiDataIntent = new Intent(this, Main4Activity.class);
        startActivity(viewSavedBmiDataIntent);
    }

    //This method is used to try using browser installed on the phone
    public void viewBmiInfo(View view) {
        Intent viewBmiIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://pl.wikipedia.org/wiki/Wska%C5%BAnik_masy_cia%C5%82a"));
        startActivity(viewBmiIntent);
    }

    //Activity is a screen in android app
    //Opening screen with webview
    public void viewAuthorInfo() {
        Intent activity2Intent = new Intent(this, Main2Activity.class);
        startActivity(activity2Intent);
    }

    //Opening screen with webview and extra info in intent
    public void viewSearchResults() {
        EditText searchField = findViewById(R.id.searchField);
        String searchUrl = "https://www.google.com/search?q=" + searchField.getText().toString() + "&cad=h";
        Intent activity3Intent = new Intent(this, Main3Activity.class);
        activity3Intent.putExtra("searchUrl", searchUrl);
        startActivity(activity3Intent);
    }

    //Saving to shared preferences
    private void saveBmiData(String name, String height, String weight, String bmi) {
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        sharedPreferencesEditor.putString(NAME, name);
        sharedPreferencesEditor.putString(HEIGHT, height);
        sharedPreferencesEditor.putString(WEIGHT, weight);
        sharedPreferencesEditor.putString(BMI, bmi);
        sharedPreferencesEditor.apply();
        showToast("Zapisano BMI");
    }

    //Show toast with message
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
