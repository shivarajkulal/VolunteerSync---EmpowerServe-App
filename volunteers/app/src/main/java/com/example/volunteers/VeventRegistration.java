package com.example.volunteers;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

public class VeventRegistration extends AppCompatActivity {
    private EditText name, Email,uname,phone,address;
//    private EditText  City ;
    private Button buttonSignUp;
    private TextView event,ngonam;
    private DatabaseReference databaseReference;
    private static final String TAG = "VeventRegis";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseReference = FirebaseDatabase.getInstance().getReference("VeventRegis");
        setContentView(R.layout.activity_vevent_registration);
        event=findViewById(R.id.eventname);
        ngonam=findViewById(R.id.ngoname);
        name=findViewById(R.id.RName);
        uname=findViewById(R.id.RUName);
        phone=findViewById(R.id.RPhone);
        Email=findViewById(R.id.REmailAddress);
//        City=findViewById(R.id.RCity);
        address=findViewById(R.id.RAddress);

        buttonSignUp=findViewById(R.id.Rregister);
//        signup=findViewById(R.id.signupregister);

        Intent itnt=getIntent();
        String eventtoberegistered=itnt.getStringExtra("eventname");
        String ngoname=itnt.getStringExtra("ngoname");
//        String eventtoberegistered="FoodDonation";
////        String ngoname="Manglore-NGO";
        event.setText(eventtoberegistered);
        ngonam.setText(ngoname);


        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eventnam, nam, unam, phn, add, email;
//                String  city;
                //eventnam = String.valueOf(event.getText());
                nam = String.valueOf(name.getText());
                unam = String.valueOf(uname.getText());
                phn = String.valueOf(phone.getText());
//                city = String.valueOf(City.getText());
                email = String.valueOf(Email.getText());
                add = String.valueOf(address.getText());

                if (nam.isEmpty() || unam.isEmpty() || phn.isEmpty() || email.isEmpty() || add.isEmpty()) {
                    Toast.makeText(VeventRegistration.this, "Enter the unfilled fields!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("users");
                    Query query = databaseRef.orderByChild("username").equalTo(unam);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                String unamvalidate = String.valueOf(uname.getText());
                                String eventnamevalidate = String.valueOf(event.getText());
                                String ngoname=String.valueOf(ngonam.getText());

                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("VeventRegis");
                                Query query2 = databaseReference.orderByChild("username").equalTo(unamvalidate);

                                query2.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        boolean isAlreadyRegistered = false;
                                        for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                            String storedeventnam = String.valueOf(userSnapshot.child("eventnam").getValue());
//                                            console message
                                            Log.d(TAG, "You are Trying to Register again! for the event:" + storedeventnam);
                                            if (eventnamevalidate.equals(storedeventnam)&&ngoname.equals(ngoname)) {
                                                isAlreadyRegistered = true;
                                                break;
                                            }
                                        }

                                        if (isAlreadyRegistered) {
                                            Toast.makeText(VeventRegistration.this, "Already Registered for the Event!", Toast.LENGTH_SHORT).show();
                                        } else {
                                            createUser();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        // Handle the error
                                    }
                                });
                            } else {
                                Toast.makeText(VeventRegistration.this, "Not a registered user!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            // Handle the error
                        }
                    });
                }
            }
        });

//        signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(VeventRegistration.this,Vsignup.class);
//                startActivity(intent);
//               // finish();
//            }
//        });

    }

    private void createUser() {
        // Get user input
        // ProgressBar.setVisibility(View.VISIBLE);
        String eventnam,nam,unam,phn,add,email,ngoname;
//        String city;
       eventnam=String.valueOf(event.getText());
        ngoname=String.valueOf(ngonam.getText());
        nam=String.valueOf(name.getText());
        unam=String.valueOf(uname.getText());
        phn=String.valueOf(phone.getText());
        //city=String.valueOf(City.getText());
        email=String.valueOf(Email.getText());
        add=String.valueOf(address.getText());


        String userId = databaseReference.push().getKey();
        // Create a User object
        if (isValidEmail(email)) {
            // Create a User object
            if(isvalidphone(phn)) {
                VeventRegis user = new VeventRegis(nam,eventnam, email, unam, phn, add,ngoname);

                // Store the user object in the database
                databaseReference.child(userId).setValue(user);

                // Show success message or navigate to the next activity
                Toast.makeText(VeventRegistration.this, "Registration Successful!"+unam, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(VeventRegistration.this,Vprofile.class);
                intent.putExtra("username",unam);
                startActivity(intent);
                //finish();
            }else{
                phone.setError("Length sholud be  10");
            }
        } else {
            Email.setError("Please enter a valid email address (Format:abc@gmail.com or abc@ac.in)");
        }

    }






    private boolean isValidEmail(String email) {
        // Use a regular expression to validate the email address format
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(emailRegex);
    }
    private boolean isvalidphone(String phn){
        //checking for min lenght is of 10 or not
        String len="10";
        String length=String.valueOf(phn.length());
        return len.matches(length);
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



