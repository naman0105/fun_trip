package com.example.naman.fun_trip;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by naman on 14-Dec-17.
 */

public class HomeActivity extends AppCompatActivity {

    private TextView welcomeMessage;

    private String WelcomeMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        welcomeMessage = findViewById(R.id.welcome_message);
        welcomeMessage.setText("hello welcome to the app, you have made an account with us ");
    }
}
