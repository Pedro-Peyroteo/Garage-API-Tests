package api.client;

import api.mappings.client.ClientResponse;
import api.retrofit.garage.Client;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.util.List;

import static api.validators.ResponseValidator.*;

public class GetClientNegativeTest
{
    @Test(description = "Get a non-existent client by id")
    public void getNonExistentClientByIdTest() {
        Response<List<ClientResponse>> getResponse = Client.getAllClients();
        assertOk(getResponse);

        ClientResponse lastClient = getResponse.body().get(getResponse.body().size() - 1);
        Integer testId = lastClient.getId() + 1;
        // TESTAR MENSAGEM DE ERRO É A ESPERADA
        Response<ClientResponse> response = Client.getClientByid(testId);
        assertNotFound(response);
    }
}
