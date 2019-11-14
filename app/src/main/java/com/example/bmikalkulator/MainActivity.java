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

    private Button bmiCalculation;
    private Button viewBmiInfo;
    private Button viewAuthor;
    private Button search;

    //Shared preferences addition
    private static final String MEMORY = "Memory";
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
        bmiCalculation = (Button) findViewById(R.id.calculateBMI);
        bmiCalculation.setOnClickListener(view -> calculateBmi(view));

        //On click - Goes to BMI Wikipedia
        viewBmiInfo = (Button) findViewById(R.id.viewBMIInfo);
        viewBmiInfo.setOnClickListener(view -> viewBmiInfo(view));

        //On click - Open new Activity with WebView - In WebView we are using html file
        viewAuthor = (Button) findViewById(R.id.viewAuthorInfo);
        viewAuthor.setOnClickListener(view -> viewAuthorInfo());

        //On click - Open new Activity with WebView - In WebView we are using google url to search
        search = (Button) findViewById(R.id.search);
        search.setOnClickListener(view -> viewSearchResults());
    }


    public void calculateBmi(View view) {
        EditText heightField = findViewById(R.id.heightField);
        EditText weightField = findViewById(R.id.weightField);
        TextView resultField = findViewById(R.id.resultField);


        if (heightField.getText().toString().isEmpty() || weightField.getText().toString().isEmpty()) {
            resultField.setTextColor(Color.RED);
            resultField.setText("Podaj poprawne wartości");
        } else {
            double height = Double.parseDouble(heightField.getText().toString());
            double weight = Double.parseDouble(weightField.getText().toString());

            double BMI = weight / (height * height) * 10000;

            resultField.setTextColor(Color.GRAY);

            if (BMI > 25) {

                resultField.setText("Nadwaga, Twoje BMI:" + Double.toString((int) BMI));

            } else if (BMI > 20) {

                resultField.setText("PrawidlowawagaTwoje BMI:" + Double.toString((int) BMI));

            } else {

                resultField.setText("NiedowagaTwoje BMI: " + Double.toString((int) BMI));

            }

            showToast("Zapisano BMI");
        }
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
    private void saveBmiData(String height, String weight, String bmi) {
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        sharedPreferencesEditor.putString(HEIGHT, height);
        sharedPreferencesEditor.putString(WEIGHT, weight);
        sharedPreferencesEditor.putString(BMI, bmi);
        sharedPreferencesEditor.commit();
    }

    //Show toast with message
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
