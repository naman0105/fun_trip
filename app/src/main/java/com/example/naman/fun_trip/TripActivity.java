package com.example.naman.fun_trip;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

/**
 * Created by naman on 17-Dec-17.
 */

public class TripActivity extends AppCompatActivity {

    private DatabaseReference mDatabase,m2Database;
    private String tripid, budget2;
    private String phonenumber;
    private String moneyspent;
    private TextView Budget;
    private TextView Destination,moneyspentbyme;
    private Button submit;
    private EditText enteredmoney;
    private String moneybythis;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);

        mDatabase = FirebaseDatabase.getInstance().getReference("trips");

        GlobalVariables a = (GlobalVariables)getApplication();
        tripid = a.getTripid();
        SharedPreferences prefs = getSharedPreferences("phoneandpass", MODE_PRIVATE);
        phonenumber = prefs.getString("phonenumber", null);
        Destination = (TextView) findViewById(R.id.destination);
        Budget = (TextView) findViewById(R.id.moneyspent);
        submit = (Button) findViewById(R.id.submit);
        enteredmoney = (EditText) findViewById(R.id.entermoney);
        moneyspentbyme = (TextView) findViewById(R.id.moneyspentbyme);

        mDatabase.child(tripid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String destination = dataSnapshot.child("Destination").getValue().toString();
                moneyspent = dataSnapshot.child("moneyspent").getValue().toString();
                moneybythis = dataSnapshot.child(phonenumber).getValue().toString();
                Destination.setText(destination);
                Budget.setText("total money spent in the group:: " +moneyspent);
                moneyspentbyme.setText("total money contributed by me::  " + moneybythis);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDatabase.child(tripid).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String destination = dataSnapshot.child("Destination").getValue().toString();
                        moneyspent = dataSnapshot.child("moneyspent").getValue().toString();
                        moneybythis = dataSnapshot.child(phonenumber).getValue().toString();
                        Destination.setText(destination);
                        Budget.setText("total money spent in the group:: " +moneyspent);
                        moneyspentbyme.setText("total money contributed by me::  " + moneybythis);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                int entered = Integer.parseInt(enteredmoney.getText().toString());
                int totalforthis = entered + Integer.parseInt(moneybythis);
                int total = entered + Integer.parseInt(moneyspent);
                String text = String.valueOf(total);
                String text1 = String.valueOf(totalforthis);
                mDatabase.child(tripid).child(phonenumber).setValue(totalforthis);
                mDatabase.child(tripid).child("moneyspent").setValue(total);
                Budget.setText("total money spent in the group:: " + text);
                moneyspentbyme.setText("total money contributed by me::  " + text1);
            }
        });

    }
}
