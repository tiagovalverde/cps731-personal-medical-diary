package com.saubantiago.personalmedicaldiary.activities.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.saubantiago.personalmedicaldiary.PasswordManager;
import com.saubantiago.personalmedicaldiary.R;
import com.saubantiago.personalmedicaldiary.Utils;
import com.saubantiago.personalmedicaldiary.database.entities.User;
import com.saubantiago.personalmedicaldiary.database.view.UserViewModal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    Button registerButton;
    EditText editTxtEmail, editTxtPassword;

    UserViewModal usersViewModal;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // initialization
        this.displayBackButton();
        this.findAllViews();
        this.setListeners();
        this.setUserObserver();
    }

    private void createAccount() {
        String email = this.editTxtEmail.getText().toString();
        String password = this.editTxtPassword.getText().toString();


        if (Utils.emailValid(this, email) && Utils.passwordValid(this, password)) {

            if (Utils.emailExists(this.users, email)) {
                Utils.showToast(this,"Email already associated with an account!");
            } else {
                String hashedPassword = PasswordManager.hashPassword(password);
                User user = new User(email, hashedPassword, "", "");
                this.usersViewModal.insert(user);
                Utils.showToast(this,"Account created! You can now login!");
                finish();
            }
        } else {
            Utils.showToast(this, "Invalid email or password!");
        }
    }

    private void findAllViews() {
        editTxtEmail = findViewById(R.id.txtEmailValue);
        editTxtPassword = findViewById(R.id.txtPasswordValue);
        registerButton = findViewById(R.id.registerButton);
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