package com.example.naman.fun_trip;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by naman on 19/11/17.
 */

public class AddTripActivity extends AppCompatActivity{


    private DatabaseReference mDatabase,m2Database;

    private String Destination,FriendPhone1,FriendPhone2,FriendPhone3;
    private int budget;
    private String DateOfReturn, DateOfDeparture;

    private static final String _CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final int RANDOM_STR_LENGTH = 12;

    private EditText t_Destination,t_FriendPhone1,t_FriendPhone2,t_FriendPhone3,t_budget,t_DateOfReturn,t_DateOfDeparture;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_trip_activity);

        mDatabase = FirebaseDatabase.getInstance().getReference("users");
        m2Database = FirebaseDatabase.getInstance().getReference("trips");

        t_Destination = (EditText) findViewById(R.id.destination);
        t_budget = (EditText) findViewById(R.id.budget);
        t_DateOfDeparture = (EditText) findViewById(R.id.date_of_departure);
        t_DateOfReturn = (EditText) findViewById(R.id.date_of_return);
        t_FriendPhone1 = (EditText) findViewById(R.id.friend_phone_1);
        t_FriendPhone2 = (EditText) findViewById(R.id.friend_phone_2);
        t_FriendPhone3 = (EditText) findViewById(R.id.friend_phone_3);
        submit = (Button) findViewById(R.id.submit_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Destination = t_Destination.getText().toString();
                budget = Integer.parseInt(t_budget.getText().toString());
                DateOfDeparture = t_DateOfDeparture.getText().toString();
                DateOfReturn = t_DateOfReturn.getText().toString();
                FriendPhone1 = t_FriendPhone1.getText().toString();
                FriendPhone2 = t_FriendPhone2.getText().toString();
                FriendPhone3 = t_FriendPhone3.getText().toString();

                GlobalVariables a = (GlobalVariables)getApplication();
                String phNumber = a.getData();
                String id = getRandomString();
                mDatabase.child(phNumber).child("Trips").setValue(id);
                m2Database.child(id).child("Destination").setValue(Destination);
                m2Database.child(id).child("Budget").setValue(budget);
                m2Database.child(id).child("DateOfReturn").setValue(DateOfReturn);
                m2Database.child(id).child("DateOfDeparture").setValue(DateOfDeparture);
                m2Database.child(id).child("moneyspent").setValue(0);
                m2Database.child(id).child(phNumber).setValue(0);

                if(FriendPhone1.matches("")){

                } else{
                    m2Database.child(id).child(FriendPhone1).setValue(0);
                }
                if(FriendPhone2.matches("")){

                } else{
                    m2Database.child(id).child(FriendPhone2).setValue(0);
                }
                if(FriendPhone3.matches("")){

                } else{
                    m2Database.child(id).child(FriendPhone3).setValue(0);
                }
                mDatabase.child(FriendPhone1).child("Trips").setValue(id);
                mDatabase.child(FriendPhone2).child("Trips").setValue(id);
                mDatabase.child(FriendPhone3).child("Trips").setValue(id);
                startActivity(new Intent(AddTripActivity.this,HomeActivity.class));
            }
        });

    }

    private int getRandomNumber() {
        int randomInt = 0;
        Random random = new Random();
        randomInt = random.nextInt(_CHAR.length());
        if (randomInt - 1 == -1) {
            return randomInt;
        } else {
            return randomInt - 1;
        }
    }

    public String getRandomString(){

        StringBuffer randStr = new StringBuffer();

        for (int i = 0; i < RANDOM_STR_LENGTH; i++) {

            int number = getRandomNumber();
            char ch = _CHAR.charAt(number);
            randStr.append(ch);
        }
        return randStr.toString();
    }


    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
        }
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }
}
