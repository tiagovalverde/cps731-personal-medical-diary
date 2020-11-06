package com.saubantiago.personalmedicaldiary.activities.caregiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.saubantiago.personalmedicaldiary.AppEnums;
import com.saubantiago.personalmedicaldiary.R;
import com.saubantiago.personalmedicaldiary.database.entities.Caregiver;

public class CaregiverEditActivity extends AppCompatActivity {
    // Data
    Caregiver caregiver;

    // Views
    Button saveButton;
    EditText editTxtFName, editTxtLName, editTxtPhone, editTxtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caregiver_edit);

        // initialization
        this.displayBackButton();
        this.findAllViews();
        this.setListeners();

        caregiver = this.getCaregiverFromIntent();
        if(caregiver != null) {
            this.preFillForm();
        }
    }

    private Caregiver getCaregiverFromIntent() {
        if (getIntent().getExtras() != null) {
            return (Caregiver) getIntent().getSerializableExtra(CaregiverDetailsActivity.EXTRA_DATA_CAREGIVER);
        } else {
            return null;
        }
    }

    private void preFillForm() {
        editTxtFName.setText(this.caregiver.getFirstName());
        editTxtLName.setText(this.caregiver.getLastName());
        editTxtPhone.setText(this.caregiver.getPhone());
        editTxtEmail.setText(this.caregiver.getEmail());
    }

    private void findAllViews() {
        saveButton = findViewById(R.id.caregiverSaveButton);
        editTxtFName = findViewById(R.id.editTextFName);
        editTxtLName = findViewById(R.id.editTextLName);
        editTxtPhone = findViewById(R.id.editTextPhone);
        editTxtEmail = findViewById(R.id.editTextEmail);
    }

    private void setListeners() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CaregiverEditActivity.this.saveChanges();
            }
        });
    }

    private void saveChanges() {
        Intent replyIntent = new Intent();
        Caregiver updatedCaregiver = this.getUpdatedCaregiver();

        if (this.caregiver == null) {
            replyIntent.putExtra(CaregiverDetailsActivity.EXTRA_DATA_ACTION, AppEnums.Actions.CREATE);
        } else {
            updatedCaregiver.setId(this.caregiver.getId());
            replyIntent.putExtra(CaregiverDetailsActivity.EXTRA_DATA_ACTION, AppEnums.Actions.UPDATE);
        }
        replyIntent.putExtra(CaregiverDetailsActivity.EXTRA_DATA_CAREGIVER, updatedCaregiver);
        setResult(RESULT_OK, replyIntent);
        finish();
    }

    private Caregiver getUpdatedCaregiver() {
        return new Caregiver(
                editTxtFName.getText().toString(),
                editTxtLName.getText().toString(),
                editTxtPhone.getText().toString(),
                editTxtEmail.getText().toString()
        );
    }

    private void displayBackButton() {
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
    }
}