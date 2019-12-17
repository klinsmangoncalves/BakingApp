package br.com.kmg.bakingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.kmg.bakingapp.R;
import br.com.kmg.bakingapp.model.ReceiptStep;

public class ReceiptStepAdapter extends RecyclerView.Adapter<ReceiptStepAdapter.ReceiptStepViewHolder>{

    private List<ReceiptStep> receiptSteps;
    private final ReceiptStepAdapter.OnReceiptStepClickListener mReceiptStepClickListener;

    public interface OnReceiptStepClickListener {
        void onReceiptStepClickListener(ReceiptStep receiptStep, int position);
    }

    public ReceiptStepAdapter(List<ReceiptStep> receiptSteps, ReceiptStepAdapter.OnReceiptStepClickListener mReceiptStepClickListener) {
        this.receiptSteps = receiptSteps;
        this.mReceiptStepClickListener = mReceiptStepClickListener;
    }

    public void setReceiptSteps(List<ReceiptStep> receiptSteps) {
        this.receiptSteps = receiptSteps;
    }

    @NonNull
    @Override
    public ReceiptStepAdapter.ReceiptStepViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.receips_step_item, viewGroup, false);
        return new ReceiptStepAdapter.ReceiptStepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiptStepAdapter.ReceiptStepViewHolder ReceiptStepViewHolder, int i) {
        ReceiptStep receiptStep = receiptSteps.get(i);
        ReceiptStepViewHolder.receiptStepTitle.setText(receiptStep.getShortDescription());
    }

    @Override
    public int getItemCount() {
        return receiptSteps.size();
    }

    class ReceiptStepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        final TextView receiptStepTitle;

        ReceiptStepViewHolder(@NonNull View itemView) {
            super(itemView);
            receiptStepTitle = itemView.findViewById(R.id.tv_receipt_step_item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int i = getAdapterPosition();
            ReceiptStep receiptStep = receiptSteps.get(i);
            mReceiptStepClickListener.onReceiptStepClickListener(receiptStep, i);
        }
    }
}
