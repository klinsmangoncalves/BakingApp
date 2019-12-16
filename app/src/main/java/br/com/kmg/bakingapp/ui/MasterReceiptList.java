package br.com.kmg.bakingapp.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import br.com.kmg.bakingapp.R;
import br.com.kmg.bakingapp.model.Receipt;
import br.com.kmg.bakingapp.model.ReceiptStep;
import br.com.kmg.bakingapp.ui.fragment.MasterReceiptListFragment;
import br.com.kmg.bakingapp.ui.fragment.StepDetailFragment;

public class MasterReceiptList extends AppCompatActivity implements MasterReceiptListFragment.OnStepClickListener {

    public static final String RECEIPT_STEP_EXTRA = "receipt_step_extra";

    public Boolean isTablet = false;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_receipt_list);
        fragmentManager = getSupportFragmentManager();

        Intent intent  = getIntent();
        Receipt receipt = (Receipt) intent.getSerializableExtra(MainActivity.RECEIPT_EXTRA);
        setupMasterList(receipt);

        if(findViewById(R.id.view_center_divider) == null){
            isTablet = false;
        } else {
            setupReceiptDetail(receipt.getSteps().get(0));
            isTablet = true;
        }

    }

    private void setupReceiptDetail(ReceiptStep step){
        StepDetailFragment stepDetailFragment = new StepDetailFragment();
        stepDetailFragment.setReceiptStep(step);
        fragmentManager.beginTransaction()
                .replace(R.id.container_detail_tablet, stepDetailFragment)
                .commit();
    }

    private void setupMasterList(Receipt receipt){
        MasterReceiptListFragment masterListFragment = new MasterReceiptListFragment();
        masterListFragment.setIngredients(receipt.getIngredients());
        masterListFragment.setSteps(receipt.getSteps());

        fragmentManager.beginTransaction()
                .add(R.id.master_list_fragment, masterListFragment)
                .commit();
    }

    @Override
    public void onStepClickListener(ReceiptStep receiptStep) {
        if(true){
            setupReceiptDetail(receiptStep);
        } else {
            Intent intent = new Intent(this, StepDetailActivity.class);
            intent.putExtra(RECEIPT_STEP_EXTRA, receiptStep);
            startActivity(intent);
        }
    }
}
