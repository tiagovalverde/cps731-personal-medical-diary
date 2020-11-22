package com.saubantiago.personalmedicaldiary.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saubantiago.personalmedicaldiary.R;
import com.saubantiago.personalmedicaldiary.activities.assessments.AssessmentsDetailsActivity;
import com.saubantiago.personalmedicaldiary.activities.assessments.AssessmentsViewActivity;
import com.saubantiago.personalmedicaldiary.activities.medicalrecords.MedicalRecordDetailsActivity;
import com.saubantiago.personalmedicaldiary.constants.Constants;
import com.saubantiago.personalmedicaldiary.database.entities.MedicalRecord;
import com.saubantiago.personalmedicaldiary.database.entities.SelfAssessments;

import java.util.List;

public class SelfAssessmentAdapter extends RecyclerView.Adapter<SelfAssessmentHolder>  {
    private final List<SelfAssessments> selfAssessments;
    private LayoutInflater inflater;
    private Context context;

    public SelfAssessmentAdapter(Context ctx, List<SelfAssessments> selfAssessments) {
        this.selfAssessments = selfAssessments;
        this.inflater = LayoutInflater.from(ctx);
        this.context = ctx;
    }

    @NonNull
    @Override
    public SelfAssessmentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.self_assessment_item, parent, false);
        return new SelfAssessmentHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull SelfAssessmentHolder holder, final int position) {
        SelfAssessments selfAssessment = this.selfAssessments.get(position);
        holder.itemTxtViewId.setText(Long.toString(selfAssessment.getId()));
        holder.itemTxtViewType.setText(selfAssessment.getAssessmentType());
        holder.itemContainerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelfAssessments selfAssessment = selfAssessments.get(position);
                Intent intent = new Intent(context, AssessmentsViewActivity.class);
                intent.putExtra(Constants.EXTRA_DATA_SELF_ASSESSMENT, selfAssessment);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return selfAssessments.size();
    }
}
