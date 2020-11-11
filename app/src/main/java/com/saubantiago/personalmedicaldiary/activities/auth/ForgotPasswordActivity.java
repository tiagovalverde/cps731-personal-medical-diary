package com.saubantiago.personalmedicaldiary.activities.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.saubantiago.personalmedicaldiary.R;
import com.saubantiago.personalmedicaldiary.Utils;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ForgotPasswordActivity extends AppCompatActivity {
    private EditText editTxtEmail;
    private Button resetPasswordButton;
    private ProgressBar progressBar;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        // init
        this.displayBackButton();
        this.findAllViews();
        this.setListeners();
        auth = FirebaseAuth.getInstance();
    }

    private void findAllViews() {
        progressBar = findViewById(R.id.resetPasswordProgressBar);
        editTxtEmail = findViewById(R.id.fp_editTxtEmail);
        resetPasswordButton = findViewById(R.id.resetButton);
    }

    private void setListeners() {
        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }

    private void resetPassword() {
        String email = editTxtEmail.getText().toString().trim();
        if (Utils.emailValid(editTxtEmail, email)) {
            progressBar.setVisibility(View.VISIBLE);
            auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(ForgotPasswordActivity.this, "Check your email to reset your password", Toast.LENGTH_LONG);
                            } else {
                                Toast.makeText(ForgotPasswordActivity.this, "Try again! Something went wrong!", Toast.LENGTH_LONG);
                            }

                        }
                    });
        }
    }


    private void displayBackButton() {
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
    }
}