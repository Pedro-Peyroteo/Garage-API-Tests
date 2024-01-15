package api.client;

import api.mappings.client.ClientRequest;
import api.mappings.client.ClientResponse;
import api.retrofit.garage.Client;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import retrofit2.Response;

import static api.helper.ClientRequests.newPositiveClient;
import static org.testng.Assert.assertEquals;
import static api.validators.ResponseValidator.*;


public class DeleteClientPositiveTest
{
    private Integer newClientId;
    private ClientRequest newClientRequest;

    @BeforeMethod
    public void setup(){
        newClientRequest = newPositiveClient();

        Response<Integer> response = Client.createClient(newClientRequest);
        assertCreated(response);
        newClientId = response.body();
    }

    @AfterMethod
    public void cleanup(){
        Response<Void> response = Client.deleteClient(newClientId);
        assertNoContent(response);

        newClientId = null;
        newClientRequest = null;
    }

    @Test(description = "ID 0017 - Delete client by ID")
    public void deleteClientById() {
        // Tenta excluir o cliente com o ID especificado
        Response<Void> deleteResponse = Client.deleteClient(newClientId);
        assertEquals(deleteResponse.code(), 204, "Failed to delete the client");
    }






}
