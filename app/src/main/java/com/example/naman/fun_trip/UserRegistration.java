package com.example.naman.fun_trip;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by naman on 28-Nov-17.
 */

public class UserRegistration extends AppCompatActivity {


    private DatabaseReference mDatabase,databaseref;
    private Button submitButton;
    private EditText et_name,et_email,et_password,et_confirmPassword,et_city;
    private String Name,Email,Password,ConfirmPassword,City;
    private TextView message;
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
                databaseref = mDatabase.child("users").child(phNumber);
                et_name = (EditText)findViewById(R.id.name);
                et_city = (EditText)findViewById(R.id.city);
                et_password = findViewById(R.id.password);
                et_email = findViewById(R.id.email);
                et_confirmPassword = findViewById(R.id.confirm_password);
                message = findViewById(R.id.message);
                Name = et_name.getText().toString();
                Email = et_email.getText().toString();
                City = et_city.getText().toString();
                Password = et_password.getText().toString();
                ConfirmPassword = et_confirmPassword.getText().toString();
                if (!Email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
                    message.setText("Invalid Email Address");
                }
                else {
                    if (Password.equals(ConfirmPassword) && Password.length() != 0) {
//                System.out.println(et_name.getText().toString());
                        databaseref.child("Name").setValue(Name);
                        databaseref.child("Email").setValue(Email);
                        databaseref.child("City").setValue(City);
                        databaseref.child("Password").setValue(Password);
                        message.setText("you have successfully registered");

                        Intent intent = new Intent(UserRegistration.this,HomeActivity.class);
                        startActivity(intent);

                    } else {
                        message.setText("password and confirm password should be same");
                    }
                }
            }
        });
    }

}
