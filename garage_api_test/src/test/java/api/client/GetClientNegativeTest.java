package api.client;

import api.mappings.ErrorResponse;
import api.mappings.client.ClientRequest;
import api.mappings.client.ClientResponse;
import api.retrofit.garage.Client;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.util.List;

import static api.helper.ClientRequests.newPositiveClient;
import static api.validators.ResponseBodyValidator.assertBodyNotNull;
import static api.validators.ResponseValidator.*;

public class GetClientNegativeTest
{
    @Test(description = "ID 0018 - Get a non-existent client by id")
    public void getNonExistentClientByIdTest() {
        Response<List<ClientResponse>> getResponse = Client.getAllClients();
        assertOk(getResponse);
        assertBodyNotNull(getResponse);

        ClientResponse lastClient = getResponse.body().get(getResponse.body().size() - 1);
        Integer testId = lastClient.getId() + 1;

        Response<ClientResponse> response = Client.getClientByid(testId);
        assertNotFound(response);

        // TODO: Testar mensagem de erro???
    }

    /*@Test(description = "Get a non-existent client by id")
    public void getNonExistentClientByIdTest1() {
        ClientRequest client = newPositiveClient();
        Response<Integer> createResponse = Client.createClient(client);
        assertCreated(createResponse);

        Response<Void> deleteResponse = Client.deleteClient(createResponse.body());
        assertNoContent(deleteResponse);

        Response<ClientResponse> response = Client.getClientByid(createResponse.body());
        assertNotFound(response);
    }*/

    @Test(description = "ID 0019 - Get a client with the id 0")
    public void getClientByIdZeroTest(){
        Response<ClientResponse> response = Client.getClientByid(0);
        assertNotFound(response);
    }

    @Test(description = "ID 0020 - Get client with a negative id")
    public void getClientWithNegativeIdTest(){
        Response<ClientResponse> response = Client.getClientByid(-1);
        assertBadRequest(response);
    }
}