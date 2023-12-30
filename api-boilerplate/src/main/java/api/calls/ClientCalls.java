package api.calls;

import api.mappings.garage.client.ClientResponse;
import api.retrofit.garage.Client;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

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
    Call<ClientResponse> createClient(@Body Client client);
}