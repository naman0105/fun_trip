package com.example.naman.fun_trip;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by naman on 28-Nov-17.
 */

public class UserRegistration extends AppCompatActivity {


    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
// ...
        mDatabase = FirebaseDatabase.getInstance().getReference();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        Bundle bundle = new Bundle();
        bundle  = getIntent().getExtras();
        String phNumber = bundle.getString("PhNumber");
        System.out.println("In user registration class "+ phNumber);
        mDatabase.child("users").setValue(phNumber);
    }
}
