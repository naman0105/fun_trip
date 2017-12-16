package com.example.naman.fun_trip;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * Created by naman on 14-Dec-17.
 */

public class HomeActivity extends AppCompatActivity {

    private TextView welcomeMessage;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users");
    private Button addTrip;

    private String WelcomeMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        welcomeMessage = (TextView) findViewById(R.id.welcome_message);
        //welcomeMessage.setText("hello welcome to the app, you have made an account with us ");
        GlobalVariables a = (GlobalVariables)getApplication();
        String phNumber = a.getData();
//        phNumber = "7760620783";
//        welcomeMessage.setText(phNumber);
        addTrip = (Button) findViewById(R.id.add_trip);

        myRef.child(phNumber).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println( "Value is: " + dataSnapshot.child("City").getValue());
                String name = dataSnapshot.child("Name").getValue().toString();
                welcomeMessage.setText("welcome " + name + "! click 'Add Trip' to add a new trip");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        addTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,AddTripActivity.class);
                startActivity(intent);
            }
        });
    }

}
