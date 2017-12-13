package com.example.naman.fun_trip;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by naman on 28-Nov-17.
 */

public class UserRegistration extends AppCompatActivity {


    private DatabaseReference mDatabase;
    private Button submitButton;
    private EditText et_name;
    private String name,email,password,confirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
// ...
        mDatabase = FirebaseDatabase.getInstance().getReference();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        Bundle bundle = new Bundle();
        bundle  = getIntent().getExtras();
        final String phNumber = bundle.getString("PhNumber");
        System.out.println("In user registration class "+ phNumber);
        mDatabase.child("users").setValue(phNumber);

        submitButton = (Button) findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_name = (EditText)findViewById(R.id.name);
                System.out.println(et_name);
                mDatabase.child("users").child(phNumber).setValue("Name",et_name);
            }
        });
    }

}
