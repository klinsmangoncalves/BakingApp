package br.com.kmg.bakingapp.service;

import java.util.List;

import br.com.kmg.bakingapp.model.Receipt;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ReceiptService {

    @GET("/topher/2017/May/59121517_baking/baking.json")
    Call<List<Receipt>> getReceiptList();
}
