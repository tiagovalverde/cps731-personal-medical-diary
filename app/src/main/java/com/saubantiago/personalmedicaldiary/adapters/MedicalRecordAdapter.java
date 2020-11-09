package com.saubantiago.personalmedicaldiary.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saubantiago.personalmedicaldiary.R;
import com.saubantiago.personalmedicaldiary.activities.medicalrecords.MedicalRecordDetailsActivity;
import com.saubantiago.personalmedicaldiary.constants.Constants;
import com.saubantiago.personalmedicaldiary.database.entities.MedicalRecord;
import com.saubantiago.personalmedicaldiary.database.entities.User;

import java.util.List;

public class MedicalRecordAdapter extends RecyclerView.Adapter<MedicalRecordHolder> {
    private final List<MedicalRecord> medicalRecords;
    private final User user;
    private LayoutInflater inflater;
    private Context context;

    public MedicalRecordAdapter(Context ctx, List<MedicalRecord> medicalRecords, User user) {
        this.medicalRecords = medicalRecords;
        this.user = user;
        this.inflater = LayoutInflater.from(ctx);
        this.context = ctx;
    }

    @NonNull
    @Override
    public MedicalRecordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.medical_record_item, parent, false);
        return new MedicalRecordHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicalRecordHolder holder, final int position) {
        MedicalRecord medicalRecord = this.medicalRecords.get(position);
        holder.itemTxtViewId.setText(Long.toString(medicalRecord.getId()));
        holder.itemTxtViewType.setText(medicalRecord.getMedicalRecordType());
        holder.itemContainerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MedicalRecord medicalRecord = medicalRecords.get(position);
                Intent intent = new Intent(context, MedicalRecordDetailsActivity.class);
                intent.putExtra(Constants.EXTRA_DATA_MEDICAL_RECORD, medicalRecord);
                intent.putExtra(Constants.EXTRA_DATA_USER, user);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return medicalRecords.size();
    }
}
