package com.saubantiago.personalmedicaldiary.activities.medicalrecords;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.saubantiago.personalmedicaldiary.AppEnums;
import com.saubantiago.personalmedicaldiary.R;
import com.saubantiago.personalmedicaldiary.constants.Constants;
import com.saubantiago.personalmedicaldiary.database.entities.MedicalRecord;
import com.saubantiago.personalmedicaldiary.database.view.MedicalRecordViewModel;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MedicalRecordEditActivity extends AppCompatActivity {
    // Views
    Button saveButton, takePictureButton;
    EditText editTextMedicalRecordType;
    ImageView imageView;

    // Data
    FirebaseUser user;
    MedicalRecord medicalRecord;
    MedicalRecordViewModel medicalRecordViewModel;

    Uri imageURI;
    File imageFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_record_edit);

        // init
        user = FirebaseAuth.getInstance().getCurrentUser();
        this.medicalRecordViewModel = ViewModelProviders.of(this).get(MedicalRecordViewModel .class);
        this.displayBackButton();
        this.findAllViews();
        this.setListeners();
        this.getMedicalRecordFromIntent();
        if (this.medicalRecord != null) {
            this.preFillForm();
        }

        // Permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            takePictureButton.setEnabled(false);
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
        }
    }

    private void preFillForm() {
        editTextMedicalRecordType.setText(this.medicalRecord.getMedicalRecordType());
        Bitmap bm = BitmapFactory.decodeFile(medicalRecord.getFileLocation());
        imageView.setImageBitmap(bm);
        imageView.setVisibility(View.VISIBLE);
    }

    private void getMedicalRecordFromIntent() {
        if (getIntent().getExtras() != null) {
            this.medicalRecord =  (MedicalRecord) getIntent().getSerializableExtra(Constants.EXTRA_DATA_MEDICAL_RECORD);
        } else {
            this.medicalRecord =  null;
        }
    }

    private void findAllViews() {
        saveButton = findViewById(R.id.mr_saveButton);
        takePictureButton = findViewById(R.id.mr_takePictureButton);
        imageView = findViewById(R.id.mr_imageView);
        editTextMedicalRecordType = findViewById(R.id.mr_editTxtType);
    }

    private void setListeners() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                saveChanges();
            }
        });

        takePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });
    }

    private void saveChanges() {
        String medicalRecordType = editTextMedicalRecordType.getText().toString();

        Intent replyIntent = new Intent();

        if (this.medicalRecord != null) {
            medicalRecord.setFileType(AppEnums.MedicalRecordType.IMAGE.toString());
            medicalRecord.setMedicalRecordType(medicalRecordType);
            medicalRecord.setUserUID(user.getUid());

            if(imageFile != null) medicalRecord.setFileLocation(imageFile.getAbsolutePath());

            medicalRecordViewModel.update(medicalRecord);
            replyIntent.putExtra(Constants.EXTRA_DATA_MEDICAL_RECORD, this.medicalRecord);
        } else {
            String newImagePath = imageFile != null ? imageFile.getAbsolutePath() : null;

            MedicalRecord newMedicalRecord = new MedicalRecord(
                    AppEnums.MedicalRecordType.IMAGE.toString(),
                    newImagePath,
                    medicalRecordType
            );
            newMedicalRecord.setUserUID(user.getUid());
            medicalRecordViewModel.insert(newMedicalRecord);
        }

        setResult(RESULT_OK, replyIntent);
        finish();
    }

    private void displayBackButton() {
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: // back button pressed
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Camera related methods
     */
    
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                takePictureButton.setEnabled(true);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Bitmap bm = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
            imageView.setImageBitmap(bm);
            imageView.setVisibility(View.VISIBLE);
        }
    }

    private void takePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        imageFile = null;

        try {
            imageFile = createImageFile();
        } catch (IOException ex) {
            Toast.makeText(this,"Error opening the Camera!", Toast.LENGTH_LONG).show();
            System.out.println(ex.getMessage());
        }

        if (imageFile != null) {
            imageURI = FileProvider.getUriForFile(
                    this,
                    this.getApplicationContext().getPackageName() + ".fileprovider",
                    imageFile
            );
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageURI);
            startActivityForResult(takePictureIntent, 100);
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "medical_record_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        return image;
    }
}