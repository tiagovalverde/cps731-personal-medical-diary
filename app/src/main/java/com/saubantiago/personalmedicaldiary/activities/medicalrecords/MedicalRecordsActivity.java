package com.saubantiago.personalmedicaldiary.activities.medicalrecords;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saubantiago.personalmedicaldiary.R;
import com.saubantiago.personalmedicaldiary.SessionManager;
import com.saubantiago.personalmedicaldiary.activities.auth.LoginActivity;
import com.saubantiago.personalmedicaldiary.adapters.MedicalRecordAdapter;
import com.saubantiago.personalmedicaldiary.constants.Constants;
import com.saubantiago.personalmedicaldiary.database.entities.MedicalRecord;
import com.saubantiago.personalmedicaldiary.database.entities.relationships.UserAndMedicalRecords;
import com.saubantiago.personalmedicaldiary.database.view.UserViewModal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecordsActivity extends AppCompatActivity {
    // Views
    private Button addButton;
    private RecyclerView medicalRecordsRecyclerView;
    private MedicalRecordAdapter medicalRecordAdapter;

    long userId;
    // live data
    UserViewModal userViewModal;
    private UserAndMedicalRecords userAndMedicalRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_records);

        // initialization
        this.getUserSession();
        this.displayBackButton();
        this.findAllViews();
        this.setListeners();
        this.setObserver();
    }

    private void setObserver() {
        userViewModal = ViewModelProviders.of(this).get(UserViewModal.class);
        userViewModal.getAllUsersAndMedicalRecords().observe(this, new Observer<List<UserAndMedicalRecords>>() {

            @Override
            public void onChanged(List<UserAndMedicalRecords> userAndMedicalRecords) {
                for(UserAndMedicalRecords user : userAndMedicalRecords) {
                    if(user.user.getId() == MedicalRecordsActivity.this.userId) {
                        MedicalRecordsActivity.this.userAndMedicalRecords = user;
                        MedicalRecordsActivity.this.populateRecyclerView();
                        return;
                    }
                }
            }
        });
    }

    private void populateRecyclerView() {
        List<MedicalRecord> medicalRecords = new ArrayList<MedicalRecord>();

        // TODO improve/refactor null check
        if (this.userAndMedicalRecords != null) {
            medicalRecords = this.userAndMedicalRecords.medicalRecords;
        }
        medicalRecordAdapter = new MedicalRecordAdapter(this, medicalRecords, this.userAndMedicalRecords.user);
        medicalRecordsRecyclerView.setAdapter(medicalRecordAdapter);
        medicalRecordsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void findAllViews() {
        addButton = findViewById(R.id.addMedicalRecordButton);
        medicalRecordsRecyclerView = findViewById(R.id.rv_medical_record_list);
    }

    private void setListeners() {
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MedicalRecordsActivity.this.launchEditActivity();
            }
        });
    }

    public void launchEditActivity() {
        Intent intent = new Intent(this, MedicalRecordEditActivity.class);
        intent.putExtra(Constants.EXTRA_DATA_USER, this.userAndMedicalRecords.user);
        startActivityForResult(intent, Constants.CREATE_UPDATE_ACTIVITY_REQUEST_CODE);
    }

    private void getUserSession() {
        if (SessionManager.getLoggedInUserStatus(this)) {
            this.userId = SessionManager.getLoggedInUserId(this);
        } else {
            this.launchLoginActivity();
        }
    }

    private void launchLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void displayBackButton() {
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
    }
}