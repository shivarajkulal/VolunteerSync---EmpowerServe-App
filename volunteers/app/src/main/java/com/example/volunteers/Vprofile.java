package com.example.volunteers;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;

public class Vprofile extends AppCompatActivity {
//    private Button b1,b2;
    private TextView Name,username,Email,Loc,Add,phn;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vprofile);
        Name=findViewById(R.id.pnameval);
        username=findViewById(R.id.punameval);
        Email=findViewById(R.id.pemailval);
        //Loc=findViewById(R.id.plocval);
        Add=findViewById(R.id.paddressval);
        phn=findViewById(R.id.pphnval);


        Intent it=getIntent();
        String loginedngo=it.getStringExtra("username");
        databaseReference= FirebaseDatabase.getInstance().getReference("users");
        Query qry = databaseReference.orderByChild("username").equalTo(loginedngo);
        //Toast.makeText(Ngoprofilepage.this, ""+loginedngo, Toast.LENGTH_SHORT).show();
        qry.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        String uname = String.valueOf(userSnapshot.child("username").getValue());
                        String name = String.valueOf(userSnapshot.child("name").getValue());
                        String email = String.valueOf(userSnapshot.child("email").getValue());
                        String address = String.valueOf(userSnapshot.child("address").getValue());
                       // String loc = String.valueOf(userSnapshot.child("location").getValue());
                        String phone = String.valueOf(userSnapshot.child("phone").getValue());
                        //Toast.makeText(Ngoprofilepage.this, "name"+ngoname, Toast.LENGTH_SHORT).show();
                        Name.setText(name);
                        username.setText(uname);
                        Email.setText(email);
                       // Loc.setText(loc);
                        Add.setText(address);
                        phn.setText(phone);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//update button
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Retrieve the updated values from the UI
//                String newName = Name.getText().toString();
//                String newEmail = Email.getText().toString();
//                String newAddress = Add.getText().toString();
//                String newLocation = Loc.getText().toString();
//
//                // Get a reference to the specific location in the database
//                DatabaseReference ngosRef = FirebaseDatabase.getInstance().getReference("nsignup");
//                Query query = ngosRef.orderByChild("ngoname").equalTo(loginedngo);
//
//                query.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        if (snapshot.exists()) {
//                            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
//                                // Update the values in the database
//                                userSnapshot.getRef().child("name").setValue(newName);
//                                userSnapshot.getRef().child("email").setValue(newEmail);
//                                userSnapshot.getRef().child("address").setValue(newAddress);
//                                userSnapshot.getRef().child("location").setValue(newLocation);
//
//                                // Show a success message
//                                Toast.makeText(Ngoprofilepage.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        // Handle any error that occurs during the update
//                        Toast.makeText(Ngoprofilepage.this, "Failed to update profile", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });




    }
    public class MyFragment extends Fragment {

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
                @Override
                public void handleOnBackPressed() {
                    // Handle the back button event
                    // Add your code here to perform the desired actions when the back button is pressed in the fragment

                    // For example, you can navigate to the previous fragment or activity
                    requireActivity().onBackPressed(); // This will navigate to the previous fragment or activity

                    // If you want to disable the callback and allow the default back button behavior, you can use the following line:
                    // this.setEnabled(false);
                }
            };

            requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
        }

        // Other fragment methods...

    }

}