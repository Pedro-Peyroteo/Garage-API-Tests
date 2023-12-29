package api.calls;

import api.mappings.client.Client;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface ClientCalls {
    String CLIENT = "client";
    String CLIENT_ID = "client/{id}";
    String ID = "id";

    @GET(CLIENT)
    Call<List<Client>> getAllClient();

    @GET(CLIENT_ID)
    Call<Client> getClientId(@Path(ID) Integer clientId);
}