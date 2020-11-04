package com.saubantiago.personalmedicaldiary.activities.caregiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.saubantiago.personalmedicaldiary.R;
import com.saubantiago.personalmedicaldiary.activities.profile.PatientProfileDetailsActivity;
import com.saubantiago.personalmedicaldiary.activities.profile.PatientProfileEditActivity;
import com.saubantiago.personalmedicaldiary.database.entities.PatientProfile;

public class CarevigerDetailsActivity extends AppCompatActivity {
    Button editBtn;
    TextView txtViewFName, txtViewLName, txtViewPhone, txtViewEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_careviger_details);

        // initialization
        this.findAllViews();
        this.setListeners();
    }

    private void findAllViews() {
        txtViewFName = findViewById(R.id.txtViewFNameValue);
        txtViewLName = findViewById(R.id.txtViewLNameValue);
        txtViewPhone = findViewById(R.id.txtViewPhoneValue);
        txtViewEmail = findViewById(R.id.txtViewEmailValue);
        editBtn = findViewById(R.id.editCaregiverBtn);
    }

    private void setListeners() {
        editBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CarevigerDetailsActivity.this.launchEditActivity();
            }
        });
    }

    public void launchEditActivity() {
        // TODO implement when caregiver data added to DB
    }

    private void displayBackButton() {
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
    }
}