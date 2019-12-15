package br.com.kmg.bakingapp.service;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private final String RECEIPT_JSON_URL = "https://d17h27t6h515a5.cloudfront.net/";

    private final Retrofit retrofit;

    public RetrofitConfig() {
       this.retrofit = new Retrofit.Builder()
               .baseUrl(RECEIPT_JSON_URL)
               .addConverterFactory(JacksonConverterFactory.create())
               .build();
    }

    public ReceiptService getReceiptService(){
        return retrofit.create(ReceiptService.class);
    }
}
