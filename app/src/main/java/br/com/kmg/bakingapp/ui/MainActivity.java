package br.com.kmg.bakingapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import br.com.kmg.bakingapp.BakingAppWidget;
import br.com.kmg.bakingapp.R;
import br.com.kmg.bakingapp.adapter.ReceiptAdapter;
import br.com.kmg.bakingapp.model.Ingredient;
import br.com.kmg.bakingapp.model.Receipt;
import br.com.kmg.bakingapp.preferences.PreferencesManagerUtil;
import br.com.kmg.bakingapp.service.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ReceiptAdapter.OnReceiptClickListener {

    RecyclerView mReceiptRecycler;
    ReceiptAdapter mReceiptAdapter;

    public static final String RECEIPT_EXTRA = "receipt_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mReceiptRecycler = findViewById(R.id.rv_receipt_list);

        loadReceipts();
    }

    private void setReceiptDataAdapter(List<Receipt> receipts){
        int orientation = getResources().getConfiguration().orientation;

        GridLayoutManager gridLayoutManager;

        //In landscape more movie posters can be set in activity, so, it checks the
        //  orientation before set the grid.
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            int GRID_LENGTH_PORTRAIT = 2;
            gridLayoutManager = new GridLayoutManager(this, GRID_LENGTH_PORTRAIT);
        } else {
            int GRID_LENGTH_LANDSCAPE = 3;
            gridLayoutManager = new GridLayoutManager(this, GRID_LENGTH_LANDSCAPE);
        }

        mReceiptRecycler.setLayoutManager(gridLayoutManager);
        mReceiptRecycler.setHasFixedSize(true);

        if (mReceiptAdapter == null) {
            mReceiptAdapter = new ReceiptAdapter(receipts, this);
            mReceiptRecycler.setAdapter(mReceiptAdapter);
        } else {
            mReceiptAdapter.setReceipts(receipts);
        }
    }

    private void loadReceipts(){
        Call<List<Receipt>> call = new RetrofitConfig().getReceiptService().getReceiptList();
        call.enqueue(new Callback<List<Receipt>>() {
            @Override
            public void onResponse(Call<List<Receipt>> call, Response<List<Receipt>> response) {
                List<Receipt> list = response.body();

                for (Receipt r : list){
                    Log.d("RECEITA", r.getName());
                }
                setReceiptDataAdapter(list);
            }

            @Override
            public void onFailure(Call<List<Receipt>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onReceiptClickListener(Receipt receipt) {
        PreferencesManagerUtil.setPreference(this, BakingAppWidget.INGREDIENT_WIDGET_KEY, getIngredientsListString(receipt));
        Intent intent = new Intent(this, MasterReceiptList.class);
        intent.putExtra(RECEIPT_EXTRA, receipt);
        startActivity(intent);
    }

    private String getIngredientsListString(Receipt receipt) {
        String str = "";
        for (Ingredient i : receipt.getIngredients()) {
            str+=i.getIngredient() + " - " + i.getQuantity() + i.getMeasure() + "\n";
        }
        return str;
    }
}
