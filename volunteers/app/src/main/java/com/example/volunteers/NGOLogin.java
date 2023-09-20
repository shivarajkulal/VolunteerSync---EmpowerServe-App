package com.example.volunteers;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;


public class NGOLogin extends AppCompatActivity {
    private Button button;
    private EditText email,pas;
    private TextView signup;

    DatabaseReference ref;
    public static final String pass_name="com.example.volunteers.name";
    FirebaseAuth firebaseAuth;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent=new Intent(NGOLogin.this,Ngoprofilepage.class);
            startActivity(intent);
           // finish();
        }
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nlogin);
        pas=findViewById(R.id.npassword1);
        email=findViewById(R.id.nEmail);
        button=findViewById(R.id.nsubmit);
        signup=findViewById(R.id.nsignupmain);
        firebaseAuth= FirebaseAuth.getInstance();

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
                }
                else{
                    readData(getuser,getpas);
                }

//                firebaseAuth.signInWithEmailAndPassword(getuser, getpas).addOnCompleteListener(new OnCompleteListener < AuthResult > () {
//
//
//                    @Override
//                    public void onComplete(@NonNull Task< AuthResult  > task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
//                            Intent intent=new Intent(MainActivity.this,Vprofile.class);
//                            startActivity(intent);
//                            finish();
//                        } else {
//                            // If sign in fails, display a message to the user.
//
//                            Toast.makeText(MainActivity.this, "Authentication failed."+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//                });

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NGOLogin.this,ngosignup.class);

                startActivity(intent);
               // finish();
            }

        });

    }

//    private void readdata(String getuser,String getpas) {
//        ref= FirebaseDatabase.getInstance().getReference("users");
//        ref.child(getuser).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if (task.isSuccessful()) {
//                            if (task.getResult().exists()) {
//                                // Sign in success, update UI with the signed-in user's information
//
//                                DataSnapshot dataSnapshot=task.getResult();
//                                String email=String.valueOf(dataSnapshot.child("email").getValue());
//                                String password=String.valueOf(dataSnapshot.child("password").getValue());
//
//                                if (getuser.equals(email)&&getpas.equals(password)){
//                                    Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(MainActivity.this, Vprofile.class);
//                                    startActivity(intent);
//                                    finish();
//                                }
//
//                            }else{
//                                Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
//                            }
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Toast.makeText(MainActivity.this, "user don't exist!", Toast.LENGTH_SHORT).show();
//
//
//                        }
//            }
//        });

    //}

    private void readData(String email, String password) {
        ref = FirebaseDatabase.getInstance().getReference("nsignup");
        Query query = ref.orderByChild("email").equalTo(email);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        String storedPassword = String.valueOf(userSnapshot.child("password").getValue());

                        if (password.equals(storedPassword)) {
                            // Authentication successful, proceed with desired actions
                            Toast.makeText(NGOLogin.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(NGOLogin.this, Vlandingpage.class);
                            String get =String.valueOf(userSnapshot.child("ngoname").getValue());
                            //Toast.makeText(NGOLogin.this, ""+get, Toast.LENGTH_SHORT).show();
                            intent.putExtra("pass_name",get);
                            startActivity(intent);
                            //finish();
                            return;
                        }
                    }
                }

                // Authentication failed, handle accordingly
                Toast.makeText(NGOLogin.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //notting
            }

        });
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