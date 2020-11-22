package com.saubantiago.personalmedicaldiary.activities.assessments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.saubantiago.personalmedicaldiary.R;
import com.saubantiago.personalmedicaldiary.activities.dashboard.Dashboard;
import com.saubantiago.personalmedicaldiary.activities.profile.PatientProfileDetailsActivity;
import com.saubantiago.personalmedicaldiary.adapters.MedicalRecordAdapter;
import com.saubantiago.personalmedicaldiary.adapters.SelfAssessmentAdapter;
import com.saubantiago.personalmedicaldiary.database.entities.MedicalRecord;
import com.saubantiago.personalmedicaldiary.database.entities.SelfAssessments;
import com.saubantiago.personalmedicaldiary.database.view.MedicalRecordViewModel;
import com.saubantiago.personalmedicaldiary.database.view.SelfAssessmentsViewModel;

import java.util.List;

public class AssessmentsDetailsActivity extends AppCompatActivity {
    // Views
    Button createAssessmentButton;
    private RecyclerView selfAssessmentsRecyclerView;
    private SelfAssessmentAdapter selfAssessmentAdapter;

    // live data
    FirebaseUser user;
    SelfAssessmentsViewModel selfAssessmentsViewModel;
    private List<SelfAssessments> selfAssessments;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessments_details);

        // initialization
        user = FirebaseAuth.getInstance().getCurrentUser();
        this.displayBackButton();
        this.findAllViews();
        this.setListeners();
        this.setObserver();
    }

    private void setObserver() {
        selfAssessmentsViewModel = ViewModelProviders.of(this).get(SelfAssessmentsViewModel.class);
        selfAssessmentsViewModel.getAllAssessmentsByUserUID(user.getUid()).observe(this, new Observer<List<SelfAssessments>>() {
            @Override
            public void onChanged(List<SelfAssessments> _selfAssessments) {
                selfAssessments= _selfAssessments;
                populateRecyclerView();
            }
        });
    }

    private void populateRecyclerView() {
        selfAssessmentAdapter = new SelfAssessmentAdapter(this, selfAssessments);
        selfAssessmentsRecyclerView.setAdapter(selfAssessmentAdapter);
        selfAssessmentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void findAllViews() {
        //Button
        createAssessmentButton = findViewById(R.id.CreateAssessment);
        selfAssessmentsRecyclerView = findViewById(R.id.rv_self_assessment_list);
    }

    private void setListeners() {
        createAssessmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AssessmentsDetailsActivity.this.launchAssessmentCreator();
            }
        });
    }
    private void launchAssessmentCreator() {
        Intent intent = new Intent(this, ChooseAssessmentsActivity.class);
        startActivity(intent);
    }
    private void displayBackButton() {
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
    }

}
