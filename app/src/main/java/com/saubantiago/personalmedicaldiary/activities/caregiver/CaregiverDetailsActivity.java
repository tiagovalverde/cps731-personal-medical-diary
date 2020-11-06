package com.saubantiago.personalmedicaldiary.activities.caregiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.saubantiago.personalmedicaldiary.AppEnums;
import com.saubantiago.personalmedicaldiary.R;
import com.saubantiago.personalmedicaldiary.database.entities.Caregiver;
import com.saubantiago.personalmedicaldiary.database.view.CaregiverViewModel;

import java.util.List;

public class CaregiverDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_DATA_CAREGIVER = "extra_data_caregiver";
    public static final String EXTRA_DATA_ACTION = "extra_data_action";
    public static final int CREATE_UPDATE_ACTIVITY_REQUEST_CODE = 1;

    // Views
    Button editBtn;
    TextView txtViewFName, txtViewLName, txtViewPhone, txtViewEmail;

    // live data
    CaregiverViewModel caregiverViewModel;
    private List<Caregiver> caregivers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_careviger_details);

        // initialization
        this.findAllViews();
        this.setListeners();
        this.displayBackButton();

        // setup caregiver live data
        caregiverViewModel = ViewModelProviders.of(this).get(CaregiverViewModel.class);
        caregiverViewModel.getAllCaregivers().observe(this, new Observer<List<Caregiver>>() {
            @Override
            public void onChanged(List<Caregiver> caregivers) {
                // update cached caregivers data
                CaregiverDetailsActivity.this.caregivers = caregivers;
                CaregiverDetailsActivity.this.preFillData();
            }
        });
    }

    private void preFillData() {
        if(this.caregiverExists()) {
            Caregiver caregiver = this.caregivers.get(0);
            txtViewFName.setText(caregiver.getFirstName());
            txtViewLName.setText(caregiver.getLastName());
            txtViewPhone.setText(caregiver.getPhone());
            txtViewEmail.setText(caregiver.getEmail());
        }
    }

    private boolean caregiverExists() {
        return this.caregivers != null && !this.caregivers.isEmpty();
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
                CaregiverDetailsActivity.this.launchEditActivity();
            }
        });
    }

    public void launchEditActivity() {
        Intent intent = new Intent(this, CaregiverEditActivity.class);
        if (this.caregiverExists()) {
            Caregiver caregiver = this.caregivers.get(0);
            intent.putExtra(EXTRA_DATA_CAREGIVER, caregiver);
        }
        startActivityForResult(intent, CREATE_UPDATE_ACTIVITY_REQUEST_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Caregiver updatedCaregiver = (Caregiver) data.getSerializableExtra(EXTRA_DATA_CAREGIVER);
        AppEnums.Actions actionType = (AppEnums.Actions) data.getSerializableExtra(EXTRA_DATA_ACTION);

        if(actionType == AppEnums.Actions.CREATE) {
            this.caregiverViewModel.insert(updatedCaregiver);
        }
        else if(actionType == AppEnums.Actions.UPDATE) {
            this.caregiverViewModel.update(updatedCaregiver);
        }
    }

    private void displayBackButton() {
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
    }
}