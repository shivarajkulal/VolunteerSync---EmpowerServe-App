package com.example.volunteers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class lanuchpage extends AppCompatActivity {
    private Button ngo,vol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lanuchpage);

        ngo=findViewById(R.id.Ngologin);
        vol=findViewById(R.id.Volunteerlogin);

        ngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(lanuchpage.this,NGOLogin.class);
                startActivity(i);
              //  finish();
            }
        });

        vol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(lanuchpage.this,MainActivity.class);
                startActivity(i);
               // finish();
            }
        });
    }
}