package api.client;

import api.mappings.client.ClientRequest;
import api.mappings.client.ClientResponse;
import api.retrofit.garage.Client;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.time.LocalDate;

import static api.helper.ClientRequests.newPositiveClient;
import static api.retrofit.garage.Client.createClient;
import static api.validators.ClientValidator.assertClientResponse;
import static api.validators.ResponseBodyValidator.assertBodyNotNull;
import static api.validators.ResponseBodyValidator.assertId;
import static api.validators.ResponseValidator.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CreateClientPositiveTest {
    private Integer newClientId;

    @AfterMethod
    public void cleanUp(){
        if (newClientId != null){
            Response<Void> response = Client.deleteClient(newClientId);
            assertNoContent(response);
        }

        newClientId = null;
    }

    @Test(description = "Create client with success")
    public void createClientTest() {
        // Dados do cliente a serem enviados na requisição
        ClientRequest addedClientRequest = newPositiveClient();

        Response<Integer> createResponse = Client.createClient(addedClientRequest);
        assertCreated(createResponse);
        assertBodyNotNull(createResponse);
        newClientId = createResponse.body();

        Response<ClientResponse> getResponse = Client.getClientByid(newClientId);
        assertOk(getResponse);

        assertClientResponse(getResponse.body(), addedClientRequest);
        assertId(getResponse.body().getId(), newClientId);
    }

    @Test(description = "Create client with the birth date set to current date")
    public void createClientWithBirthDateSetToCurrentDateTest(){
        ClientRequest addedClientRequest = newPositiveClient();
        addedClientRequest.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())));

        Response<Integer> createResponse = Client.createClient(addedClientRequest);
        assertCreated(createResponse);
        assertBodyNotNull(createResponse);
        newClientId = createResponse.body();

        Response<ClientResponse> getResponse = Client.getClientByid(newClientId);
        assertOk(getResponse);

        assertClientResponse(getResponse.body(), addedClientRequest);
        assertId(getResponse.body().getId(), newClientId);
    }

    @Test(description = "Create client with the birth date set to a future date")
    public void createClientWithBirthDateSetToFutureDateTest(){
        ClientRequest addedClientRequest = newPositiveClient();
        addedClientRequest.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").format(Date.valueOf(LocalDate.now().plusDays(1))));

        Response<Integer> createResponse = Client.createClient(addedClientRequest);
        assertCreated(createResponse);
        assertBodyNotNull(createResponse);
        newClientId = createResponse.body();

        Response<ClientResponse> getResponse = Client.getClientByid(newClientId);
        assertOk(getResponse);

        assertClientResponse(getResponse.body(), addedClientRequest);
        assertId(getResponse.body().getId(), newClientId);
    }

    @Test(description = "Create client with the client date set to current date")
    public void createClientWithClientDateSetToCurrentDateTest(){
        ClientRequest addedClientRequest = newPositiveClient();
        addedClientRequest.setClientDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())));

        Response<Integer> createResponse = Client.createClient(addedClientRequest);
        assertCreated(createResponse);
        assertBodyNotNull(createResponse);
        newClientId = createResponse.body();

        Response<ClientResponse> getResponse = Client.getClientByid(newClientId);
        assertOk(getResponse);

        assertClientResponse(getResponse.body(), addedClientRequest);
        assertId(getResponse.body().getId(), newClientId);
    }
}
