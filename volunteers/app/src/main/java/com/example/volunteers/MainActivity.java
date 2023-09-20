package com.example.volunteers;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private EditText email,pas;
    private TextView signup;

    DatabaseReference ref;
    String pass_name;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pas=findViewById(R.id.password1);
        email=findViewById(R.id.Email);
        button=findViewById(R.id.button);
        signup=findViewById(R.id.signupmain);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getuser =String.valueOf(email.getText());
                String getpas =String.valueOf(pas.getText());


                if (getuser.isEmpty()) {
                    email.setError("Email is required");

                    return;
                }
                if (getpas.isEmpty()) {
                    pas.setError("Password is required");
                    return;
                }
                else{
                    readData(getuser,getpas);
                }



            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Vsignup.class);
                startActivity(intent);
               // finish();
            }

        });

        }


    private void readData(String email, String password) {
        ref = FirebaseDatabase.getInstance().getReference("users");
        Query query = ref.orderByChild("email").equalTo(email);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        String storedPassword = String.valueOf(userSnapshot.child("password").getValue());

                        if (password.equals(storedPassword)) {
                            // Authentication successful, proceed with desired actions
                            Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, Vlandingpage.class);
                            String get =String.valueOf(userSnapshot.child("username").getValue());
                            //Toast.makeText(NGOLogin.this, ""+get, Toast.LENGTH_SHORT).show();
                            intent.putExtra("pass_name",get);

                            startActivity(intent);
                            //finish();
                            return;
                        }
                    }
                }

                // Authentication failed, handle accordingly
                Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
            }
              @Override
            public void onCancelled(@NonNull DatabaseError error) {
                    //notting
            }

        });
    }


    }

