package com.saubantiago.personalmedicaldiary.adapters;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saubantiago.personalmedicaldiary.R;

public class MedicalRecordHolder extends RecyclerView.ViewHolder {
    public final LinearLayout itemContainerView;
    public final TextView itemTxtViewId, itemTxtViewType;
    final MedicalRecordAdapter adapter;


    public MedicalRecordHolder(@NonNull View itemView, MedicalRecordAdapter adapter) {
        super(itemView);
        this.adapter = adapter;
        itemContainerView = itemView.findViewById(R.id.rv_medical_record_container);
        itemTxtViewId = itemView.findViewById(R.id.rv_txtViewItemId);
        itemTxtViewType = itemView.findViewById(R.id.rv_txtViewItemType);
    }
}
