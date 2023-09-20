package com.example.volunteers;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class publicngoview extends AppCompatActivity {

    private TextView Name,Ngoname,Email,Add;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicngoview);
        Name = findViewById(R.id.npnameval);
        Ngoname = findViewById(R.id.Ngonameval);
        Email = findViewById(R.id.npemailval);
        // Loc=findViewById(R.id.nplocval);
        Add = findViewById(R.id.npaddressval);
//        b1=findViewById(R.id.edit);
        //b2 = findViewById(R.id.addevent);


        Intent it = getIntent();
        String loginedngo = it.getStringExtra("username");
        //String loginedngo="hai";
        Log.d(TAG, "onCreate: " + loginedngo);


        databaseReference = FirebaseDatabase.getInstance().getReference("nsignup");
        Query qry = databaseReference.orderByChild("ngoname").equalTo(loginedngo);
        //Toast.makeText(Ngoprofilepage.this, ""+loginedngo, Toast.LENGTH_SHORT).show();
        qry.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        String ngoname = String.valueOf(userSnapshot.child("ngoname").getValue());
                        String name = String.valueOf(userSnapshot.child("name").getValue());
                        String email = String.valueOf(userSnapshot.child("email").getValue());
                        String address = String.valueOf(userSnapshot.child("address").getValue());
                        // String loc = String.valueOf(userSnapshot.child("location").getValue());
                        //String address = String.valueOf(userSnapshot.child("ngoname").getValue());
                        //Toast.makeText(Ngoprofilepage.this, "name"+ngoname, Toast.LENGTH_SHORT).show();
                        Name.setText(name);
                        Ngoname.setText(ngoname);
                        Email.setText(email);
                        // Loc.setText(loc);
                        Add.setText(address);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}