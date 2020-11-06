package com.saubantiago.personalmedicaldiary.activities.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.saubantiago.personalmedicaldiary.R;
import com.saubantiago.personalmedicaldiary.activities.dashboard.Dashboard;

public class Login extends AppCompatActivity {

    // Views
    TextView txtViewUsername, txtViewPassword;
    Button loginButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // initialization
        this.findAllViews();
        this.setListeners();
    }

    private void findAllViews() {
        //Views
        txtViewUsername = findViewById(R.id.textViewUsernameLabel);
        txtViewPassword = findViewById(R.id.textViewPasswordLabel);
        //Button
        loginButton = findViewById(R.id.loginButton);

    }

    private void setListeners() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login.this.launchDashboard();
            }
        });
    }

    private void launchDashboard() {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
        finish();
    }


}
