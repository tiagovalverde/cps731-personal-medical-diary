package com.saubantiago.personalmedicaldiary.activities.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.saubantiago.personalmedicaldiary.R;
import com.saubantiago.personalmedicaldiary.SessionManager;
import com.saubantiago.personalmedicaldiary.Utils;
import com.saubantiago.personalmedicaldiary.activities.dashboard.Dashboard;
import com.saubantiago.personalmedicaldiary.database.entities.User;
import com.saubantiago.personalmedicaldiary.database.view.UserViewModal;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    // TODO Add splash screen functionality
    // Views
    TextView txtViewEmail, txtViewPassword;
    Button loginButton, registerButton;

    // live data
    UserViewModal usersViewModal;
    private List<User> users;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.isLoggedIn();

        // initialization
        this.findAllViews();
        this.setListeners();
        this.setUserObserver();
    }

    private void findAllViews() {
        //Views
        txtViewEmail = findViewById(R.id.editTextEmailValue);
        txtViewPassword = findViewById(R.id.editTextPasswordValue);
        //Button
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.btnOpenRegisterScreen);
    }

    private void setListeners() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.login();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.launchRegisterScreen();
            }
        });


    }

    private void setUserObserver() {
        usersViewModal = ViewModelProviders.of(this).get(UserViewModal.class);
        usersViewModal.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                LoginActivity.this.users = users;
            }
        });
    }

    private void launchDashboard() {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
        finish();
    }

    private void launchRegisterScreen() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void isLoggedIn() {
        if(SessionManager.getLoggedInUserStatus(this)) {
            this.launchDashboard();
            finish();
        }
    }

    private void login() {
        String email = this.txtViewEmail.getText().toString();
        String password = this.txtViewPassword.getText().toString();

        if (Utils.emailValid(this, email) && Utils.passwordValid(this, password)) {

            User user = Utils.getUser(this, this.users, email, password);
            if (user != null) {
                SessionManager.setLoggedInUserEmail(this, user.getEmail());
                SessionManager.setLoggedInUserId(this, user.getId());
                SessionManager.setLoggedInUserStatus(this, true);
                this.launchDashboard();
                finish();
            }
        }
    }
}
