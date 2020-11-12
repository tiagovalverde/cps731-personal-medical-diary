package com.saubantiago.personalmedicaldiary.activities.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.saubantiago.personalmedicaldiary.R;
import com.saubantiago.personalmedicaldiary.Utils;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    Button registerButton;
    EditText editTxtEmail, editTxtPassword;
    ProgressBar progressBar;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // initialization
        auth = FirebaseAuth.getInstance();
        this.displayBackButton();
        this.findAllViews();
        this.setListeners();
    }

    private void createAccount() {
        final String email = this.editTxtEmail.getText().toString();
        final String password = this.editTxtPassword.getText().toString();

        if(!Utils.emailValid(editTxtEmail, email) || !Utils.passwordValid(editTxtPassword, password)) {
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(RegisterActivity.this, "User has been registered!", Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Toast.makeText(RegisterActivity.this, "Failed to register! Please try again!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }


    private void findAllViews() {
        editTxtEmail = findViewById(R.id.txtEmailValue);
        editTxtPassword = findViewById(R.id.txtPasswordValue);
        registerButton = findViewById(R.id.registerButton);
        progressBar = findViewById(R.id.registerProgressBar);
    }

    private void setListeners() {
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });
    }

    private void displayBackButton() {
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
    }
}