package com.example.naman.fun_trip;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by naman on 16-Dec-17.
 */

public class SigninActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users");
    EditText t_phoneNumber;
    EditText t_password;
    private String phoneNumber;
    private String password;
    TextView message;
    Button submit;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        t_phoneNumber = (EditText) findViewById(R.id.phone_number);
        t_password =(EditText) findViewById(R.id.login_password);


        message = (TextView) findViewById(R.id.message);
        submit = (Button) findViewById(R.id.submit_button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                phoneNumber = t_phoneNumber.getText().toString();
                password = t_password.getText().toString();

                GlobalVariables a = (GlobalVariables)getApplication();
                a.setData(phoneNumber);


                myRef.child(phoneNumber).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String Password = (String)dataSnapshot.child("Password").getValue();
                        System.out.println(password + "   " + Password);
                        if(password.equals(Password)){
                            startActivity(new Intent(SigninActivity.this,HomeActivity.class));
                        }
                        else{
                            message.setText("you have entered wrong password");
                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });





    }




}
