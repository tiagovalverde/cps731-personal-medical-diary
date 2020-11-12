package com.saubantiago.personalmedicaldiary.activities.medicalrecords;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.saubantiago.personalmedicaldiary.R;
import com.saubantiago.personalmedicaldiary.SessionManager;
import com.saubantiago.personalmedicaldiary.activities.auth.LoginActivity;
import com.saubantiago.personalmedicaldiary.adapters.MedicalRecordAdapter;
import com.saubantiago.personalmedicaldiary.constants.Constants;
import com.saubantiago.personalmedicaldiary.database.entities.MedicalRecord;
import com.saubantiago.personalmedicaldiary.database.view.MedicalRecordViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecordsListActivity extends AppCompatActivity {
    // Views
    private Button addButton;
    private RecyclerView medicalRecordsRecyclerView;
    private MedicalRecordAdapter medicalRecordAdapter;

    // live data
    FirebaseUser user;
    MedicalRecordViewModel medicalRecordViewModel;
    private List<MedicalRecord> medicalRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_records_list);

        // initialization
        user = FirebaseAuth.getInstance().getCurrentUser();
        this.displayBackButton();
        this.findAllViews();
        this.setListeners();
        this.setObserver();
    }

    private void setObserver() {
        medicalRecordViewModel = ViewModelProviders.of(this).get(MedicalRecordViewModel.class);
        medicalRecordViewModel.getAllCaregiversByUserUID(user.getUid()).observe(this, new Observer<List<MedicalRecord>>() {
            @Override
            public void onChanged(List<MedicalRecord> _medicalRecords) {
                medicalRecords = _medicalRecords;
                populateRecyclerView();
            }
        });
    }

    private void populateRecyclerView() {
        medicalRecordAdapter = new MedicalRecordAdapter(this, medicalRecords);
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
                MedicalRecordsListActivity.this.launchEditActivity();
            }
        });
    }

    public void launchEditActivity() {
        Intent intent = new Intent(this, MedicalRecordEditActivity.class);
        startActivityForResult(intent, Constants.CREATE_UPDATE_ACTIVITY_REQUEST_CODE);
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