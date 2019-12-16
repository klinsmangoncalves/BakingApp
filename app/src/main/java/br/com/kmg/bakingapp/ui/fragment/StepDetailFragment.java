package br.com.kmg.bakingapp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import br.com.kmg.bakingapp.R;
import br.com.kmg.bakingapp.model.ReceiptStep;

public class StepDetailFragment extends Fragment {

    private ReceiptStep receiptStep;

    public StepDetailFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_detail, container, false);
        TextView tvShortName = view.findViewById(R.id.tv_short_name_step);
        TextView tvStepDescription = view.findViewById(R.id.tv_step_description);

        tvShortName.setText(receiptStep.getShortDescription());
        tvStepDescription.setText(receiptStep.getDescription());
        return view;
    }

    public ReceiptStep getReceiptStep() {
        return receiptStep;
    }

    public void setReceiptStep(ReceiptStep receiptStep) {
        this.receiptStep = receiptStep;
    }
}
