package com.example.volunteers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;

public class guidlines extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidlines);
        String guidelines = getString(R.string.Guidlines);
        Spanned formattedGuidelines = Html.fromHtml(guidelines);

        TextView guidelinesTextView = findViewById(R.id.Guidlines);
        guidelinesTextView.setText(formattedGuidelines);



    }
}