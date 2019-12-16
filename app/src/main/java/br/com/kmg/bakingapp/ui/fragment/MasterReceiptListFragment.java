package br.com.kmg.bakingapp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.kmg.bakingapp.R;
import br.com.kmg.bakingapp.adapter.IngredientAdapter;
import br.com.kmg.bakingapp.adapter.ReceiptStepAdapter;
import br.com.kmg.bakingapp.model.Ingredient;
import br.com.kmg.bakingapp.model.ReceiptStep;

public class MasterReceiptListFragment extends Fragment implements ReceiptStepAdapter.OnReceiptStepClickListener {

    private List<Ingredient> ingredients;
    private List<ReceiptStep> steps;
    private RecyclerView mRvIngredients;
    private RecyclerView mRvSteps;

    private OnStepClickListener mListener;
    public interface OnStepClickListener{
        void onStepClickListener(ReceiptStep receiptStep);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mListener =(OnStepClickListener) context;
        }catch (ClassCastException e){
            e.printStackTrace();
        }
    }

    public MasterReceiptListFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_receipt_steps, container, false);
        mRvIngredients = fragmentView.findViewById(R.id.rv_ingredients);
        mRvSteps = fragmentView.findViewById(R.id.rv_receipt_steps);

        if(ingredients != null){
            IngredientAdapter ingredientAdapter = new IngredientAdapter(ingredients);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(inflater.getContext());
            mRvIngredients.setLayoutManager(linearLayoutManager);
            mRvIngredients.setHasFixedSize(true);
            mRvIngredients.setAdapter(ingredientAdapter);
        }

        if(steps != null){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(inflater.getContext());
            mRvSteps.setLayoutManager(linearLayoutManager);
            mRvSteps.setHasFixedSize(true);
            ReceiptStepAdapter receiptAdapter = new ReceiptStepAdapter(steps, this);
            mRvSteps.setAdapter(receiptAdapter);
        }

        return fragmentView;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<ReceiptStep> getSteps() {
        return steps;
    }

    public void setSteps(List<ReceiptStep> steps) {
        this.steps = steps;
    }

    @Override
    public void onReceiptStepClickListener(ReceiptStep receiptStep) {
        mListener.onStepClickListener(receiptStep);
    }
}
