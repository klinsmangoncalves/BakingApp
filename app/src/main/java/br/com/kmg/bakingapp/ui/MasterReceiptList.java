package br.com.kmg.bakingapp.ui;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.view.View;

import br.com.kmg.bakingapp.R;
import br.com.kmg.bakingapp.model.Receipt;
import br.com.kmg.bakingapp.ui.fragment.MasterReceiptListFragment;

public class MasterReceiptList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_receipt_list);
        Intent intent  = getIntent();
        Receipt receipt = (Receipt) intent.getSerializableExtra(MainActivity.RECEIPT_EXTRA);
        setupSinglePhoneLayout(receipt);

    }

    private void setupSinglePhoneLayout(Receipt receipt){
        MasterReceiptListFragment masterListFragment = new MasterReceiptListFragment();
        masterListFragment.setIngredients(receipt.getIngredients());
        masterListFragment.setSteps(receipt.getSteps());

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.master_list_fragment, masterListFragment)
                .commit();


    }

}
