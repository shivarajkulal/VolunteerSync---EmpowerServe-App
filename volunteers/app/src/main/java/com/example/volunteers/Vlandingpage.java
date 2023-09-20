package com.example.volunteers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Vlandingpage extends AppCompatActivity implements RecyclerLandingPageAdapter.OnItemClickListener {
    private RecyclerView recyclerView;
    private DatabaseReference ref;
    private RecyclerLandingPageAdapter adapter;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private TextView profile,logout,guidlines,NearbyNgo,Location;
    public int Flag=0;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vlandingpage);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        drawerLayout=findViewById(R.id.drawerlayout);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



//        adapter = new RecyclerLandingPageAdapter();
//        adapter.setOnJoinButtonClickListener(this);

        int verticalSpacing = getResources().getDimensionPixelSize(R.dimen.item_vertical_spacing);
        recyclerView.addItemDecoration(new SpaceItemDecoration(verticalSpacing));


        adapter = new RecyclerLandingPageAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

        //getting the username from the login page
        Intent it=getIntent();
         username=it.getStringExtra("pass_name");

        ref = FirebaseDatabase.getInstance().getReference("users");
        Query query = ref.orderByChild("username").equalTo(username);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    //user loggedin if this was false then flag will be zero indicates ngologin
                    Flag = 1;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //drawer feileds
          profile=findViewById(R.id.Vprofile);
          profile.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                          if (Flag==1) {

                              Intent it = new Intent(Vlandingpage.this, Vprofile.class);
                              it.putExtra("username", username);
                              startActivity(it);
                          }else{
                              Intent it = new Intent(Vlandingpage.this, Ngoprofilepage.class);
                              it.putExtra("username", username);
                              startActivity(it);
                          }

              }
          });

         Location = findViewById(R.id.loc);
          Location.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  try {
                      Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "abc@gmail.com"));
//                      intent.putExtra(Intent.EXTRA_SUBJECT, "your_subject");
//                      intent.putExtra(Intent.EXTRA_TEXT, "your_text");
                      startActivity(intent);
                  } catch(Exception e) {
                      Toast.makeText(Vlandingpage.this, "Gmail App is not installed", Toast.LENGTH_SHORT).show();
                      e.printStackTrace();
                  }
              }
          });
          guidlines=findViewById(R.id.Guidlines);
          guidlines.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                    Intent it=new Intent(Vlandingpage.this, guidlines.class);
                    startActivity(it);
              }
          });
          NearbyNgo=findViewById(R.id.NearbyNgo);
          NearbyNgo.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  String placeName="ngos";

                  String url = "http://maps.google.com/maps?daddr="+placeName;
                  Intent intent = new Intent(android.content.Intent.ACTION_VIEW,  Uri.parse(url));
                  startActivity(intent);
              }
          });
          logout=findViewById(R.id.Logout);
          logout.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  ref = FirebaseDatabase.getInstance().getReference("users");
                  Query query = ref.orderByChild("username").equalTo(username);
                  query.addListenerForSingleValueEvent(new ValueEventListener() {
                      @Override
                      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                          if (dataSnapshot.exists()) {

                              Intent it=new Intent(Vlandingpage.this,MainActivity.class);
                              it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                              startActivity(it);
                              finish();
                          }else{

                              Intent it=new Intent(Vlandingpage.this,NGOLogin.class);
                              it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                              startActivity(it);
                              finish();
                          }


                      }

                      @Override
                      public void onCancelled(@NonNull DatabaseError error) {

                      }
                  });

              }
          });

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("EventPost");
        // Query query = databaseReference.orderByChild("ngoname").equalTo(loginedngo); // Replace "loginedngo" with your actual variable
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    List<EventPost> eventList = new ArrayList<>();

                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        String Nname = String.valueOf(userSnapshot.child("ngoname").getValue());
                        String event = String.valueOf(userSnapshot.child("eventname").getValue());
                        String doc = String.valueOf(userSnapshot.child("doc").getValue());
                        String address = String.valueOf(userSnapshot.child("address").getValue());
                        String vreq = String.valueOf(userSnapshot.child("volreq").getValue());
                        String phone = String.valueOf(userSnapshot.child("phone").getValue());

                        EventPost eventPost = new EventPost(Nname, event, doc, phone, vreq, address);
                        eventList.add(eventPost);
                    }

                    adapter.setEventList(eventList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


        public void ngonameclick(EventPost event){
            Intent intent = new Intent(Vlandingpage.this, publicngoview.class);

            //intent.putExtra("eventname", event.getEventname());
            intent.putExtra("username", event.getNgoname());
            startActivity(intent);
        }
        @Override
        public void onItemClick(EventPost event) {
            // Handle the item click here
            // For example, start a new activity or navigate to another fragment
                        if (Flag==1) {
                            Intent intent = new Intent(Vlandingpage.this, VeventRegistration.class);

                            intent.putExtra("eventname", event.getEventname());
                            intent.putExtra("ngoname", event.getNgoname());
                            startActivity(intent);

                        }
//                        else {
//                            Intent intent = new Intent(Vlandingpage.this, Ngoprofilepage.class);
//
//                            //intent.putExtra("eventname", event.getEventname());
//                            intent.putExtra("username", event.getNgoname());
//                            startActivity(intent);
//                        }


        }


}



