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
import br.com.kmg.bakingapp.model.Receipt;

public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptAdapter.ReceiptViewHolder>{

    private List<Receipt> receipts;
    private final OnReceiptClickListener mReceiptClickListener;

    public interface OnReceiptClickListener {
        void onReceiptClickListener(Receipt receipt);
    }

    public ReceiptAdapter(List<Receipt> receipts, OnReceiptClickListener mReceiptClickListener) {
        this.receipts = receipts;
        this.mReceiptClickListener = mReceiptClickListener;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }

    @NonNull
    @Override
    public ReceiptViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.receipt_item, viewGroup, false);
        return new ReceiptViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiptViewHolder ReceiptViewHolder, int i) {
        Receipt receipt = receipts.get(i);
        ReceiptViewHolder.receiptTitle.setText(receipt.getName());
    }

    @Override
    public int getItemCount() {
        return receipts.size();
    }

    class ReceiptViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        final TextView receiptTitle;

        ReceiptViewHolder(@NonNull View itemView) {
            super(itemView);
            receiptTitle = itemView.findViewById(R.id.tv_receipt_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int i = getAdapterPosition();
            Receipt receipt = receipts.get(i);
            mReceiptClickListener.onReceiptClickListener(receipt);
        }
    }
}
