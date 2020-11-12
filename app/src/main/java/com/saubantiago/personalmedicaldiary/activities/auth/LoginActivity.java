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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.saubantiago.personalmedicaldiary.R;
import com.saubantiago.personalmedicaldiary.Utils;
import com.saubantiago.personalmedicaldiary.activities.dashboard.Dashboard;

public class LoginActivity extends AppCompatActivity {
    // Views
    EditText editTxtEmail, editTxtPassword;
    Button loginButton, registerButton, forgotPasswordButton;
    ProgressBar progressBar;

    // Data
    private FirebaseAuth auth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // initialization
        auth = FirebaseAuth.getInstance();
        this.findAllViews();
        this.setListeners();
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
                login();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchRegisterScreen();
            }
        });

        forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
            }
        });
    }

    private void launchDashboard() {
        startActivity(new Intent(this, Dashboard.class));
        finish();
    }

    private void launchRegisterScreen() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
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
                                    launchDashboard();
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
