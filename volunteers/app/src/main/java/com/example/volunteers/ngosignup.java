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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

public class ngosignup extends AppCompatActivity {
    private EditText name, Email, Password,Ngonam,phone,address;
//    private EditText location;
    private Button buttonSignUp;
    private TextView login;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseReference = FirebaseDatabase.getInstance().getReference("nsignup");
        setContentView(R.layout.activity_ngosignup);
        name=findViewById(R.id.NSName);
        Ngonam=findViewById(R.id.NgoName);
        phone=findViewById(R.id.nPhone);
        Email=findViewById(R.id.nEmailAddress);
       // location=findViewById(R.id.nloc);
        address=findViewById(R.id.nAddress);
        Password=findViewById(R.id.nPassword);
        buttonSignUp=findViewById(R.id.nregister);
        login=findViewById(R.id.nsignupregister);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nam,Ngoname,phn,add,email,pass;
                String loc;
                nam=String.valueOf(name.getText());
                Ngoname=String.valueOf(Ngonam.getText());
                phn=String.valueOf(phone.getText());
//                loc=String.valueOf(location.getText());
                email=String.valueOf(Email.getText());
                add=String.valueOf(address.getText());
                pass=String.valueOf(Password.getText());

                if(nam.isEmpty()||Ngoname.isEmpty()||phn.isEmpty()||email.isEmpty()||add.isEmpty()||pass.isEmpty()){
                    Toast.makeText(ngosignup.this, "Enter the unfilled feilds!", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    databaseReference=FirebaseDatabase.getInstance().getReference("nsignup");
                    Query query=databaseReference.orderByChild("ngoname").equalTo(Ngoname);

                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists()) {
                                    Toast.makeText(ngosignup.this, "Account Exists !!", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    createngo();
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
                Intent intent=new Intent(ngosignup.this,NGOLogin.class);
                startActivity(intent);
               // finish();
            }
        });


    }

    private  void createngo() {
        // Get user input
        // ProgressBar.setVisibility(View.VISIBLE);
        String nam,Ngoname,phn,add,email,pass;
//        String loc;
        nam=String.valueOf(name.getText());
        Ngoname=String.valueOf(Ngonam.getText());
        phn=String.valueOf(phone.getText());
       /// loc=String.valueOf(location.getText());
        email=String.valueOf(Email.getText());
        add=String.valueOf(address.getText());
        pass=String.valueOf(Password.getText());

        String userId = databaseReference.push().getKey();


        if (isValidEmail(email)) {
            // Create a User object
            if(isvalidphone(phn)) {
                if(isvalidpassword(pass)) {
                    nsignup user = new nsignup(nam, email, pass, Ngoname, phn, add);

                    // Store the user object in the database
                    databaseReference.child(userId).setValue(user);

                    // Show success message or navigate to the next activity
                    Toast.makeText(ngosignup.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ngosignup.this, NGOLogin.class);
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
        String passwd=String.valueOf(Password);
        String pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@!#$%^&*+><])[a-zA-Z0-9@!#$%^&*+><]{6,}$";
//        if (len.matches(length)&& )
        return passwd.matches(pattern);
    }
    class MyFragment extends Fragment {

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
                @Override
                public void handleOnBackPressed() {
                    // Handle the back button event
                    // Add your code here to perform the desired actions when the back button is pressed in the fragment

                    // For example, you can navigate to the previous fragment or activity
                    //Intent it=new Intent(ngosignup.this,NGOLogin.class);
                    //startActivity(it);
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



