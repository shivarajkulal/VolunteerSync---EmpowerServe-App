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

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

public class Vsignup extends AppCompatActivity {
    private EditText name, Email, Password,uname,phone,address;
    private Button buttonSignUp;
    private TextView login;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        setContentView(R.layout.activity_vsignup);
        name=findViewById(R.id.Name);
        uname=findViewById(R.id.UName);
        phone=findViewById(R.id.Phone);
        Email=findViewById(R.id.EmailAddress);
//        location=findViewById(R.id.loc);
        address=findViewById(R.id.Address);
        Password=findViewById(R.id.Password);
        buttonSignUp=findViewById(R.id.register);
        login=findViewById(R.id.signupregister);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nam,unam,phn,loc,add,email,pass;
                nam=String.valueOf(name.getText());
                unam=String.valueOf(uname.getText());
                phn=String.valueOf(phone.getText());
//                loc=String.valueOf(location.getText());
                email=String.valueOf(Email.getText());
                add=String.valueOf(address.getText());
                pass=String.valueOf(Password.getText());

                if(nam.isEmpty()||unam.isEmpty()||phn.isEmpty()||email.isEmpty()||add.isEmpty()||pass.isEmpty()){
                    Toast.makeText(Vsignup.this, "Enter the unfilled feilds!", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    databaseReference=FirebaseDatabase.getInstance().getReference("users");
                    Query query=databaseReference.orderByChild("username").equalTo(unam);

                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()) {
                                Toast.makeText(Vsignup.this, "Account Exists !!", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                createUser();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }


            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Vsignup.this,MainActivity.class);
                startActivity(intent);
                //finish();
            }
        });

    }

    private void createUser() {
        // Get user input
       // ProgressBar.setVisibility(View.VISIBLE);
        String nam,unam,phn,add,email,pass;
        nam=String.valueOf(name.getText());
        unam=String.valueOf(uname.getText());
        phn=String.valueOf(phone.getText());
//        loc=String.valueOf(location.getText());
        email=String.valueOf(Email.getText());
        add=String.valueOf(address.getText());
        pass=String.valueOf(Password.getText());

        String userId = databaseReference.push().getKey();
        // Create a User object

        if (isValidEmail(email)) {
            // Create a User object
            if(isvalidphone(phn)) {
                if(isvalidpassword(pass)){
                User user = new User(nam, email, pass, unam, phn, add);

                // Store the user object in the database
                databaseReference.child(userId).setValue(user);

                // Show success message or navigate to the next activity
                Toast.makeText(Vsignup.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Vsignup.this,MainActivity.class);
                startActivity(intent);
                //finish();
                } else{
                    Password.setError("Password  minlength 6,should contain atleast 1alphabet,1smalletter,1splchar!");
                }
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
    private boolean isvalidpassword(String Password){
//        String len="4";
        String passwd=String.valueOf(Password);
        String pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@!#$%^&*+><])[a-zA-Z0-9@!#$%^&*+><]{6,}$";
//        if (len.matches(length)&& )
        return passwd.matches(pattern);
    }
        public class MyFragments extends Fragment {
             @Override
            public void onCreate(@NonNull Bundle savedInstanceState) {

                 super.onCreate(savedInstanceState);
                 OnBackPressedCallback cllback=new OnBackPressedCallback(true) {
                     @Override
                     public void handleOnBackPressed() {
                         requireActivity().onBackPressed();
                     }
                 };
                 requireActivity().getOnBackPressedDispatcher().addCallback(this,cllback);
             }
        }

    }



