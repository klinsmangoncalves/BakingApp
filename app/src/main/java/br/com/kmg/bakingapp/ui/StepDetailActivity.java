package br.com.kmg.bakingapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import br.com.kmg.bakingapp.R;
import br.com.kmg.bakingapp.model.Receipt;
import br.com.kmg.bakingapp.model.ReceiptStep;
import br.com.kmg.bakingapp.ui.fragment.StepDetailFragment;

public class StepDetailActivity extends AppCompatActivity {

    Receipt currentReceipt;
    int currentStepPosition;
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_step_detail);

        if(bundle != null && bundle.containsKey(MasterReceiptList.RECEIPT_STEP_EXTRA)
                && bundle.containsKey(MasterReceiptList.STEP_POSITION_EXTRA)){
            currentReceipt = (Receipt) bundle.getSerializable(MasterReceiptList.RECEIPT_STEP_EXTRA);
            currentStepPosition = (int) bundle.getSerializable(MasterReceiptList.STEP_POSITION_EXTRA);
        } else {
            Intent intent = getIntent();
            currentReceipt = (Receipt) intent.getSerializableExtra(MasterReceiptList.RECEIPT_STEP_EXTRA);
            currentStepPosition = (int) intent.getSerializableExtra(MasterReceiptList.STEP_POSITION_EXTRA);
        }

        setStepDetailFragment(currentReceipt, currentStepPosition);

    }


    private void setStepDetailFragment(Receipt receipt, int stepPosition){
        StepDetailFragment stepDetailFragment =  new StepDetailFragment();
        stepDetailFragment.setReceiptStep(receipt.getSteps().get(stepPosition));

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container_single_step_detail, stepDetailFragment)
                .commit();
    }

    public void btBackClick(View view) {
        if(currentStepPosition > 0){
            currentStepPosition--;
            setStepDetailFragment(currentReceipt, currentStepPosition);
        }
    }
    public void btNextClick(View view) {
        if(currentStepPosition < currentReceipt.getSteps().size() -1 ){
            currentStepPosition++;
            setStepDetailFragment(currentReceipt, currentStepPosition);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable(MasterReceiptList.RECEIPT_STEP_EXTRA, currentReceipt);
        outState.putSerializable(MasterReceiptList.STEP_POSITION_EXTRA, currentStepPosition);
    }
}
