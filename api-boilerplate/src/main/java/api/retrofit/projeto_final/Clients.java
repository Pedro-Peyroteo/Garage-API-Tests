package api.retrofit.projeto_final;
import api.calls.ClientCalls;
import api.mappings.client.Client;
import api.retrofit.RetrofitBuilder;
import lombok.SneakyThrows;
import retrofit2.Response;

import java.util.List;

public class Clients {
    public static final ClientCalls clientCalls = new RetrofitBuilder().getRetrofit().create(ClientCalls.class);

    @SneakyThrows
    public static Response<Client> getClientByid(Integer clientId)
    {
        return clientCalls.getClientId(clientId).execute();
    }

    @SneakyThrows
    public static Response<List<Client>> getAllClient(){
        return clientCalls.getAllClient().execute();
    }
}