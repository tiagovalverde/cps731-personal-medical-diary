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
import com.saubantiago.personalmedicaldiary.SessionManager;
import com.saubantiago.personalmedicaldiary.activities.auth.LoginActivity;
import com.saubantiago.personalmedicaldiary.database.entities.Caregiver;
import com.saubantiago.personalmedicaldiary.database.entities.relationships.UserAndCaregivers;
import com.saubantiago.personalmedicaldiary.database.view.UserViewModal;

import java.util.List;

public class CaregiverDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_DATA_CAREGIVER = "extra_data_caregiver";
    public static final String EXTRA_DATA_ACTION = "extra_data_action";
    public static final int CREATE_UPDATE_ACTIVITY_REQUEST_CODE = 1;

    long userId;

    // Views
    Button editBtn;
    TextView txtViewFName, txtViewLName, txtViewPhone, txtViewEmail;

    // live data
    UserViewModal userViewModal;
    private UserAndCaregivers userAndCaregivers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_careviger_details);

        // initialization
        this.getUserSession();
        this.findAllViews();
        this.setListeners();
        this.displayBackButton();
        this.setObserver();
    }

    private void setObserver() {
        userViewModal = ViewModelProviders.of(this).get(UserViewModal.class);
        userViewModal.getAllUsersAndCaregivers().observe(this, new Observer<List<UserAndCaregivers>>() {
            @Override
            public void onChanged(List<UserAndCaregivers> userAndCaregivers) {
                for(UserAndCaregivers userAndCaregiver : userAndCaregivers) {
                    if(userAndCaregiver.user.getId() == CaregiverDetailsActivity.this.userId) {
                        CaregiverDetailsActivity.this.userAndCaregivers = userAndCaregiver;
                        CaregiverDetailsActivity.this.preFillData();
                        return;
                    }
                }
            }
        });
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

    private void preFillData() {
        if(this.caregiverExists()) {
            Caregiver caregiver = this.userAndCaregivers.caregivers.get(0);
            txtViewFName.setText(caregiver.getFirstName());
            txtViewLName.setText(caregiver.getLastName());
            txtViewPhone.setText(caregiver.getPhone());
            txtViewEmail.setText(caregiver.getEmail());
        }
    }

    private boolean caregiverExists() {
        return this.userAndCaregivers != null && !this.userAndCaregivers.caregivers.isEmpty();
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
            Caregiver caregiver = this.userAndCaregivers.caregivers.get(0);
            intent.putExtra(EXTRA_DATA_CAREGIVER, caregiver);
        }
        startActivityForResult(intent, CREATE_UPDATE_ACTIVITY_REQUEST_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Caregiver updatedCaregiver = (Caregiver) data.getSerializableExtra(EXTRA_DATA_CAREGIVER);
        AppEnums.Actions actionType = (AppEnums.Actions) data.getSerializableExtra(EXTRA_DATA_ACTION);

        if(actionType == AppEnums.Actions.CREATE) {
            this.userViewModal.insertCaregiver(this.userAndCaregivers.user, updatedCaregiver);
        }
        else if(actionType == AppEnums.Actions.UPDATE) {
            this.userViewModal.updateCaregiver(this.userAndCaregivers.user, updatedCaregiver);
        }
    }

    private void displayBackButton() {
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
    }
}