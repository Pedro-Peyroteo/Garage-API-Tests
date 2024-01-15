package api.client;

import static api.helper.ClientRequests.newPositiveClient;
import static api.validators.ResponseBodyValidator.*;
import static api.validators.ResponseValidator.*;

import api.mappings.client.ClientRequest;
import org.testng.annotations.Test;

import api.retrofit.garage.Client;
import retrofit2.Response;

public class DeleteClientNegativeTest {
    private Integer newClientId;

    @Test(description = "ID 0014 - Delete client with negative ID")
    public void deleteClientWithNegativeId() {
        newClientId = -1; 

        // Tenta excluir o cliente com o ID especificado
        Response<Void> deleteResponse = Client.deleteClient(newClientId);

        // Verifica se a exclusão foi bem-sucedida
        assertNotFound(deleteResponse);
    }

    @Test(description = "ID 0015 - Delete a non-existent client by id")
    public void deleteNonExistentClientByIdTest() {
        ClientRequest client = newPositiveClient();

        Response<Integer> createResponse = Client.createClient(client);
        assertCreated(createResponse);
        newClientId = createResponse.body();
        Integer clientIDToTest = createResponse.body();
        assertIdNotNull(clientIDToTest);

        Response<Void> deleteResponse = Client.deleteClient(clientIDToTest);
        assertNotFound(deleteResponse);
    }

        @Test(description = "ID 0016 - Delete client with ID equals 0")
        public void deleteClientWithId0() {
            newClientId = 0;

            // Tenta excluir o cliente com o ID especificado
            Response<Void> deleteResponse = Client.deleteClient(newClientId);

            // Verifica se a exclusão foi bem-sucedida
            assertNotFound(deleteResponse);
        }
}