package com.expiredfridge.calls;

import com.expiredfridge.mappings.Food; # nome da API que formos usar
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface VeichleCalls
{
    String 'nome' = "'nome'";
    String 'nome'_ID = "'nome'/{id}";
    String ID = "id";


    @GET('nome'_ID)
    Call<Food> getVeichleId(@Path(ID) Integer nomeID);

    @GET('nome')
    Call<List<Food>> getAllVeichles();

    @POST('nome')
    Call<Veichle> createVeichles(@Body nome nome);

}
