package br.com.kmg.bakingapp.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import br.com.kmg.bakingapp.R;
import br.com.kmg.bakingapp.model.ReceiptStep;
import br.com.kmg.bakingapp.ui.fragment.StepDetailFragment;

public class StepDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        Intent intent = getIntent();
        ReceiptStep step = (ReceiptStep) intent.getSerializableExtra(MasterReceiptList.RECEIPT_STEP_EXTRA);;

        StepDetailFragment stepDetailFragment =  new StepDetailFragment();
        stepDetailFragment.setReceiptStep(step);

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.container_single_step_detail, stepDetailFragment)
                .commit();

    }

}
