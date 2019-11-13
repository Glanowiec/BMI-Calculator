package com.example.bmikalkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.PublicKey;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button bmiCalculation;
    private Button viewBmiInfo;
    private Button viewAuthor;
    private Button search;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //On click BMI calculation
        bmiCalculation = (Button) findViewById(R.id.calculateBMI);
        bmiCalculation.setOnClickListener(view -> calculateBmi(view));

        //On click - Goes to BMI Wikipedia
        viewBmiInfo = (Button) findViewById(R.id.viewBMIInfo);
        viewBmiInfo.setOnClickListener(view -> viewBmiInfo(view));

        //
        viewAuthor = (Button) findViewById(R.id.viewAuthorInfo);
        viewAuthor.setOnClickListener(view -> openActivity2());


        search = (Button) findViewById(R.id.search);
        search.setOnClickListener(this);
    }



    public void calculateBmi(View view) {
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

    public void viewBmiInfo(View view){
        Intent viewBmiIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://pl.wikipedia.org/wiki/Wska%C5%BAnik_masy_cia%C5%82a"));
        startActivity(viewBmiIntent);
    }
    //Activity is a screen in android app
    //Opening screen with webview
    public void openActivity2(){
        Intent activity2Intent = new Intent(this, Main2Activity.class);
        startActivity(activity2Intent);
    }

    @Override
    public void onClick(View v) {

    }
}
