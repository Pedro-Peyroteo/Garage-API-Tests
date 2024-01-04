package api.calls;

import api.mappings.client.ClientRequest;
import api.mappings.client.ClientResponse;
import api.retrofit.garage.Client;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ClientCalls {
    String CLIENT = "client";
    String CLIENT_ID = "client/{id}";
    String ID = "id";

    @GET(CLIENT)
    Call<List<ClientResponse>> getAllClient();

    @GET(CLIENT_ID)
    Call<ClientResponse> getClientId(@Path(ID) Integer clientId);

    @POST(CLIENT)
    Call<Integer> createClient(@Body ClientRequest client);

    @DELETE(CLIENT_ID)
    Call<Void> deleteClient(@Path(ID) Integer clientId);  // Call <void> pois o metodo retorna null
}