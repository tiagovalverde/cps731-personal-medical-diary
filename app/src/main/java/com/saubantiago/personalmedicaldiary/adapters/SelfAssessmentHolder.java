package com.saubantiago.personalmedicaldiary.adapters;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saubantiago.personalmedicaldiary.R;

public class SelfAssessmentHolder extends RecyclerView.ViewHolder  {
    public final LinearLayout itemContainerView;
    public final TextView itemTxtViewId, itemTxtViewType;
    final SelfAssessmentAdapter adapter;

    public SelfAssessmentHolder(@NonNull View itemView, SelfAssessmentAdapter adapter) {
        super(itemView);
        this.adapter = adapter;
        itemContainerView = itemView.findViewById(R.id.rv_self_assessment_container);
        itemTxtViewId = itemView.findViewById(R.id.rv_txtViewItemIdSF);
        itemTxtViewType = itemView.findViewById(R.id.rv_txtViewItemTypeSF);
    }
}
