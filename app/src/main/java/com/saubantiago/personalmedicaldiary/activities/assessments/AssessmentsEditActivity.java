package com.saubantiago.personalmedicaldiary.activities.assessments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.saubantiago.personalmedicaldiary.R;

public class AssessmentsEditActivity extends AppCompatActivity {
    // Views
    Button patientProfileButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_assessments);

        // initialization
        String value= getIntent().getStringExtra("getData");
        System.out.println(value);
        this.findAllViews();
        this.setListeners();
    }

    private void findAllViews() {
        //Button

    }

    private void setListeners() {

    }
    
}
