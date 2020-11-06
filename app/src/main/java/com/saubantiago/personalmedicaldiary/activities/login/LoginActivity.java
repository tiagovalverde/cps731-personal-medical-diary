package com.saubantiago.personalmedicaldiary.activities.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.saubantiago.personalmedicaldiary.R;
import com.saubantiago.personalmedicaldiary.SessionManager;
import com.saubantiago.personalmedicaldiary.Utils;
import com.saubantiago.personalmedicaldiary.activities.dashboard.Dashboard;

public class LoginActivity extends AppCompatActivity {
    // TODO Add splash screen functionality
    // Views
    TextView txtViewEmail, txtViewPassword;
    Button loginButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.isLoggedIn();

        // initialization
        this.findAllViews();
        this.setListeners();
    }

    private void findAllViews() {
        //Views
        txtViewEmail = findViewById(R.id.editTextEmailValue);
        txtViewPassword = findViewById(R.id.editTextPasswordValue);
        //Button
        loginButton = findViewById(R.id.loginButton);
    }

    private void setListeners() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.login();
            }
        });
    }

    private void launchDashboard() {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
        finish();
    }

    private void isLoggedIn() {
        if(SessionManager.getLoggedInUserStatus(this)) {
            this.launchDashboard();
            finish();
        }
    }

    private void login() {
        // TODO user registration not implemented, just create user in db by email
        String email = this.txtViewEmail.getText().toString();
        if (Utils.emailValid(this, email)) {
            // TODO create session
            // TODO add new user in db, if not already exists
            SessionManager.setLoggedInUserEmail(this, email);
            SessionManager.setLoggedInUserId(this, 1);
            SessionManager.setLoggedInUserStatus(this, true);

            // Temporarily assume login is successful
            LoginActivity.this.launchDashboard();
        }
    }
}
