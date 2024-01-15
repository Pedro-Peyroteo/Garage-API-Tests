package api.client;

import api.mappings.ErrorResponse;
import api.mappings.client.ClientRequest;
import api.mappings.client.ClientResponse;
import api.retrofit.garage.Client;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.time.LocalDate;

import static api.helper.ClientRequests.newPositiveClient;
import static api.helper.ErrorClientResponses.errorClientInvalidBirthDate;
import static api.retrofit.garage.Error.getErrorResponse;
import static api.validators.ErrorResponseValidator.assertErrorResponse;
import static api.validators.ResponseBodyValidator.*;
import static api.validators.ResponseValidator.*;

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

    @Test(description = "ID 0010 - Create client with success")
    public void createClientTest() {
        // Dados do cliente a serem enviados na requisição
        ClientRequest addedClientRequest = newPositiveClient();

        Response<Integer> createResponse = Client.createClient(addedClientRequest);
        assertCreated(createResponse);
        assertBodyNotNull(createResponse);
        newClientId = createResponse.body();

        Response<ClientResponse> getResponse = Client.getClientByid(newClientId);
        assertOk(getResponse);

        //assertClientResponse(getResponse.body(), addedClientRequest);
        assertId(getResponse.body().getId(), newClientId);
    }

    @Test(description = "ID 0002 - Create client with the birth date set to a past day")
    public void createClientWithBirthDateLessThanToday() throws IOException {
        ClientRequest addedClientRequest = newPositiveClient();
        addedClientRequest.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").format(Date.valueOf(LocalDate.now().minusYears(18))));

        Response<Integer> createResponse = Client.createClient(addedClientRequest);
        assertBodyNull(createResponse);
        assertOk(createResponse);
    }

    @Test(description = "ID 0011 - Create client with the birth date set to current date")
    public void createClientWithBirthDateSetToCurrentDateTest(){
        ClientRequest addedClientRequest = newPositiveClient();
        addedClientRequest.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())));

        Response<Integer> createResponse = Client.createClient(addedClientRequest);
        assertCreated(createResponse);
        assertBodyNotNull(createResponse);
        newClientId = createResponse.body();

        Response<ClientResponse> getResponse = Client.getClientByid(newClientId);
        assertOk(getResponse);

        //assertClientResponse(getResponse.body(), addedClientRequest);
        assertId(getResponse.body().getId(), newClientId);
    }


    @Test(description = "ID 0012 - Create client with the client date set to current date")
    public void createClientWithClientDateSetToCurrentDateTest(){
        ClientRequest addedClientRequest = newPositiveClient();
        addedClientRequest.setClientDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())));

        Response<Integer> createResponse = Client.createClient(addedClientRequest);
        assertCreated(createResponse);
        assertBodyNotNull(createResponse);
        newClientId = createResponse.body();

        Response<ClientResponse> getResponse = Client.getClientByid(newClientId);
        assertOk(getResponse);

        //assertClientResponse(getResponse.body(), addedClientRequest);
        assertId(getResponse.body().getId(), newClientId);
    }

    @Test(description = "ID 0013 - Create client with the client date set to past date")
    public void createClientWithClientDateSetToPastDateTest(){
        ClientRequest addedClientRequest = newPositiveClient();
        addedClientRequest.setClientDate(new SimpleDateFormat("yyyy-MM-dd").format(Date.valueOf(LocalDate.now().minusDays(7))));

        Response<Integer> createResponse = Client.createClient(addedClientRequest);
        assertCreated(createResponse);
        assertBodyNotNull(createResponse);
        newClientId = createResponse.body();

        Response<ClientResponse> getResponse = Client.getClientByid(newClientId);
        assertOk(getResponse);

        //assertClientResponse(getResponse.body(), addedClientRequest);
        assertId(getResponse.body().getId(), newClientId);
    }
    


}