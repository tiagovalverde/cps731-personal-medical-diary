package com.saubantiago.personalmedicaldiary.activities.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.saubantiago.personalmedicaldiary.R;
import com.saubantiago.personalmedicaldiary.SessionManager;
import com.saubantiago.personalmedicaldiary.Utils;
import com.saubantiago.personalmedicaldiary.activities.dashboard.Dashboard;
import com.saubantiago.personalmedicaldiary.database.entities.User;
import com.saubantiago.personalmedicaldiary.database.view.UserViewModal;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    // TODO Add splash screen functionality
    EditText editTxtEmail, editTxtPassword;
    Button loginButton, registerButton, forgotPasswordButton;
    ProgressBar progressBar;

    // live data
    UserViewModal usersViewModal;
    private List<User> users;

    private FirebaseAuth auth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // TODO remove ????
        //this.isLoggedIn();
        auth = FirebaseAuth.getInstance();


        // initialization
        this.findAllViews();
        this.setListeners();
        this.setUserObserver();
    }

    private void findAllViews() {
        editTxtEmail = findViewById(R.id.editTextEmailValue);
        editTxtPassword = findViewById(R.id.editTextPasswordValue);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.btnOpenRegisterScreen);
        forgotPasswordButton = findViewById(R.id.btnForgotPassword);
        progressBar = findViewById(R.id.loginProgressBar);
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

        forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
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
        String email = editTxtEmail.getText().toString().trim();
        String password = editTxtPassword.getText().toString().trim();

        if (Utils.emailValid(editTxtEmail, email) && Utils.passwordValid(editTxtPassword, password)) {
            progressBar.setVisibility(View.VISIBLE);
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                progressBar.setVisibility(View.GONE);
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                if(user.isEmailVerified()) {
                                    LoginActivity.this.launchDashboard();
                                } else {
                                    user.sendEmailVerification();
                                    Toast.makeText(LoginActivity.this, "Check your email to verify the account!", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "Failed to Login! Please check your credentials!", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
        }
    }
}
