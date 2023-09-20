package com.example.volunteers;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

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

public class Eventposting extends AppCompatActivity {
    private EditText Nname, Email,Doc,phone,vreq,address;
    private Button buttonSignUp;
    private TextView signup,event;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseReference = FirebaseDatabase.getInstance().getReference("EventPost");
        setContentView(R.layout.activity_eventposting);
        event=findViewById(R.id.EventName);
        Nname=findViewById(R.id.Engoname);
        Doc=findViewById(R.id.Dateofconduct);
        phone=findViewById(R.id.EnPhone);
        Email=findViewById(R.id.EEmailAddress);
        vreq=findViewById(R.id.vrequired);
        address=findViewById(R.id.Address);

        buttonSignUp=findViewById(R.id.post);
        signup=findViewById(R.id.signupregister);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Ngoname,eve,phn,doc,vr,add,email;

                //eventnam=String.valueOf(event.getText());
                Ngoname=String.valueOf(Nname.getText());
                eve=String.valueOf(event.getText());
                phn=String.valueOf(phone.getText());
                vr=String.valueOf(vreq.getText());
                doc=String.valueOf(Doc.getText());
                email=String.valueOf(Email.getText());
                add=String.valueOf(address.getText());


                if(Ngoname.isEmpty()||eve.isEmpty()||phn.isEmpty()||doc.isEmpty()||vr.isEmpty()||email.isEmpty()||add.isEmpty()){
                    Toast.makeText(Eventposting.this, "Enter the unfilled feilds!", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    databaseReference=FirebaseDatabase.getInstance().getReference("nsignup");
                    Query qry=databaseReference.orderByChild("ngoname").equalTo(Ngoname);

                    qry.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()){
                                databaseReference=FirebaseDatabase.getInstance().getReference("EventPost");
                                Query query=databaseReference.orderByChild("eventname").equalTo(eve);

                                query.addListenerForSingleValueEvent(new ValueEventListener() {
                                    boolean isAlreadyposted = false;
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.exists()){
                                            for(DataSnapshot usersnapshot:snapshot.getChildren()){

                                                String doc_database=String.valueOf(usersnapshot.child("doc").getValue());
                                                String place_database=String.valueOf(usersnapshot.child("address").getValue());
                                                Log.d(TAG, "You are Trying to Register again! for the event:" + place_database);
                                                if (doc_database.equals(doc)&& place_database.equals(add)){
                                                    isAlreadyposted = true;
                                                    break;
                                                }
                                            }
                                            if(isAlreadyposted){
                                                Toast.makeText(Eventposting.this, "Already Posted", Toast.LENGTH_SHORT).show();
                                            }else{
                                                createEvent();
                                            }
                                        }else {
                                            createEvent();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                            }else{
                                Toast.makeText(Eventposting.this, "Not a registered Ngo!!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }


            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Eventposting.this,NGOLogin.class);
                startActivity(intent);
                //finish();
            }
        });

    }

    private void createEvent() {
        // Get user input
        // ProgressBar.setVisibility(View.VISIBLE);
        String Ngoname,doc,eve,phn,vr,add,email;

        // eventnam=String.valueOf(event.getText());
        Ngoname=String.valueOf(Nname.getText());
        eve=String.valueOf(event.getText());
        phn=String.valueOf(phone.getText());
        vr=String.valueOf(vreq.getText());
        doc=String.valueOf(Doc.getText());
        email=String.valueOf(Email.getText());
        add=String.valueOf(address.getText());


        String userId = databaseReference.push().getKey();
        // Create a User object
        if (isValidEmail(email)) {
            // Create a User object
            if(isvalidphone(phn)) {
                EventPost ep = new EventPost(Ngoname,eve,doc , phn, vr , add, email);

                // Store the user object in the database
                databaseReference.child(userId).setValue(ep);

                // Show success message or navigate to the next activity
                Toast.makeText(Eventposting.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Eventposting.this,Vlandingpage.class);
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



