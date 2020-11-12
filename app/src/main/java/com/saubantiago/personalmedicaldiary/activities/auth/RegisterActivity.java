package com.saubantiago.personalmedicaldiary.activities.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.saubantiago.personalmedicaldiary.R;
import com.saubantiago.personalmedicaldiary.Utils;
import com.saubantiago.personalmedicaldiary.database.entities.User;
import com.saubantiago.personalmedicaldiary.database.view.UserViewModal;
import com.saubantiago.personalmedicaldiary.firebase.UserDocument;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    Button registerButton;
    EditText editTxtEmail, editTxtPassword;
    ProgressBar progressBar;

    UserViewModal usersViewModal;
    private List<User> users;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();

        // initialization
        this.displayBackButton();
        this.findAllViews();
        this.setListeners();
        this.setUserObserver();
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
                            UserDocument user = new UserDocument(email, password);
                            // TODO this step might not be needed
                            FirebaseDatabase.getInstance()
                                    .getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()) {
                                                Toast.makeText(RegisterActivity.this, "User has been registered!", Toast.LENGTH_LONG).show();
                                                finish();
                                            } else {
                                                Toast.makeText(RegisterActivity.this, "Failed to register! Please try again!", Toast.LENGTH_LONG).show();
                                            }
                                            progressBar.setVisibility(View.GONE);
                                        }
                                    });

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
                RegisterActivity.this.createAccount();
            }
        });
    }

    private void setUserObserver() {
        usersViewModal = ViewModelProviders.of(this).get(UserViewModal.class);
        usersViewModal.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                RegisterActivity.this.users = users;
            }
        });
    }

    private void displayBackButton() {
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
    }
}