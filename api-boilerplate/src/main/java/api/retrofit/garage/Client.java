package api.retrofit.garage;
import api.calls.ClientCalls;
import api.mappings.client.ClientRequest;
import api.mappings.client.ClientResponse;
import api.retrofit.RetrofitBuilder;
import lombok.SneakyThrows;
import retrofit2.Response;

import java.util.List;

public class Client {
    public static final ClientCalls clientCalls = new RetrofitBuilder().getRetrofit().create(ClientCalls.class);

    @SneakyThrows
    public static Response<ClientResponse> getClientByid(Integer clientId)
    {
        return clientCalls.getClientId(clientId).execute();
    }

    @SneakyThrows
    public static Response<List<ClientResponse>> getAllClients(){
        return clientCalls.getAllClient().execute();
    }

    @SneakyThrows
    public static Response<ClientResponse> createClient(ClientRequest client)
    {
        return clientCalls.createClient(client).execute();
    }
}
