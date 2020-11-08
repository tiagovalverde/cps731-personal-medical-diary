package com.saubantiago.personalmedicaldiary.activities.assessments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.saubantiago.personalmedicaldiary.R;

public class ChooseAssessmentsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    // Views
    Button createChosenAssessmentButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_assessments);

        // initialization
        Spinner spinner = this.setSpinner();
        this.findAllViews();
        this.setListeners(spinner);
    }

    private Spinner setSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.AssessmentsSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.assessments_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new ChooseAssessmentsActivity());
        return spinner;
    }
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        String selected = parent.getItemAtPosition(pos).toString();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    private void findAllViews() {
        //Button
        createChosenAssessmentButton = findViewById(R.id.CreateChosenAssessment);

    }

    private void setListeners(final Spinner spinner) {
        createChosenAssessmentButton.setOnClickListener(new View.OnClickListener() {
            final String asmnt = spinner.getSelectedItem().toString();
            @Override
            public void onClick(View v) {
                ChooseAssessmentsActivity.this.launchAssessmentQuestionnaire(asmnt);
            }
        });
    }
    private void launchAssessmentQuestionnaire(String asmnt) {
        Intent intent = new Intent(this, AssessmentsEditActivity.class);
        intent.putExtra("getData", asmnt.toString());
        startActivity(intent);
    }
}

