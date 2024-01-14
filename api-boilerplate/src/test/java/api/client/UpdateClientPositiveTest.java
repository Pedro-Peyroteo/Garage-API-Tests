package api.client;
import api.mappings.client.ClientRequest;
import api.mappings.client.ClientResponse;
import api.retrofit.garage.Client;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import retrofit2.Response;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.sql.Date;

import static api.helper.ClientRequests.newPositiveClient;
import static api.validators.ClientValidator.assertClientResponse;
import static api.validators.ResponseValidator.*;

public class UpdateClientPositiveTest {
    private Integer createdClientID;
    private ClientRequest createdClientRequest;

    @BeforeMethod
    public void setup() throws IOException, ParseException {
        // Cria um novo cliente
        createdClientRequest = newPositiveClient();
        Response<Integer> createResponse = Client.createClient(createdClientRequest);
        assertCreated(createResponse);
        createdClientID = createResponse.body();
    }

    @AfterMethod
    public void cleanup() {
        // Deleta o cliente criado
        Response<Void> deleteResponse = Client.deleteClient(createdClientID);
        assertNoContent(deleteResponse);

        createdClientID = null;
        createdClientRequest = null;
    }

    @Test(description = "ID 0033 - Update client with success")
    public void updateClientTest() throws IOException, ParseException {
        // Atualiza o cliente com novos dados
        ClientRequest updatedClientRequest = new ClientRequest();
        updatedClientRequest.setFirstName("Jose");
        updatedClientRequest.setLastName("Marques");
        updatedClientRequest.setAddress("Rua das Barrocas");
        updatedClientRequest.setPostalCode("3587-008");
        updatedClientRequest.setCity("Agueda");
        updatedClientRequest.setCountry("Portugal");
        updatedClientRequest.setPhoneNumber(916234785);
        updatedClientRequest.setNif(275713741);
        updatedClientRequest.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())));
        updatedClientRequest.setClientDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())));

        // Tenta atualizar o cliente
        Response<Integer> updateResponse = Client.updateClient(createdClientID, updatedClientRequest);
        assertOk(updateResponse);

        // Verifica se a atualização foi bem-sucedida
        Response<ClientResponse> getResponse = Client.getClientByid(createdClientID);
        assertOk(getResponse);
        assertClientResponse(getResponse.body(), updatedClientRequest);
    }

    @Test(description = "ID 0029 - Update client with client date with current date")
    public void updateClientWithClientDateWithCurrentDateTest() throws IOException {
        ClientRequest updatedClientRequest = createdClientRequest;
        updatedClientRequest.setClientDate(new SimpleDateFormat("yyyy-MM-dd").format(Date.valueOf(LocalDate.now().plusDays(7))));

        Response<Integer> updateResponse = Client.updateClient(createdClientID, updatedClientRequest);
        assertOk(updateResponse);
    }
}