package api.client;

import api.mappings.ErrorResponse;
import api.mappings.client.ClientRequest;
import api.mappings.client.ClientResponse;
import api.retrofit.garage.Client;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import retrofit2.Response;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static api.helper.ClientRequests.newPositiveClient;
import static api.helper.ErrorClientResponses.*;
import static api.validators.ErrorResponseValidator.assertErrorResponse;
import static api.validators.ResponseBodyValidator.*;
import static api.validators.ResponseValidator.*;
import static api.retrofit.garage.Error.getErrorResponse;

public class CreateClientNegativeTest {
    private Integer newClientId, newClientIdHelper;
    private List<Integer> newClientIds = new ArrayList<>();

    @AfterMethod
    public void cleanUp(){
        if (newClientId != null){
            System.out.println("CLIENT ID: " + newClientId);
            Response<Void> response = Client.deleteClient(newClientId);
            assertNoContent(response);
            System.out.println("WAS DELETED");
        } else if (newClientIdHelper != null) {
            Response<Void> response = Client.deleteClient(newClientIdHelper);
            assertNoContent(response);
        }

        newClientId = null;
        newClientIdHelper = null;
    }

    @Test(description = "ID 0001 - Create client with null fields")
    public void createClientNullFieldsTest() throws Exception{
        ClientRequest addedClientRequest = ClientRequest.builder().build();

        Response<Integer> response = Client.createClient(addedClientRequest);
        assertBadRequest(response);
        newClientId = response.body();

        ErrorResponse expectedError = errorClientInvalidBody();
        assertErrorResponse(getErrorResponse(response), expectedError);
    }

    @Test(description = "ID 0003 - Create client with the client date set to future date")
    public void createClientWithClientDateSetToFutureDateTest(){
        ClientRequest addedClientRequest = newPositiveClient();
        addedClientRequest.setClientDate(new SimpleDateFormat("yyyy-MM-dd").format(Date.valueOf(LocalDate.now().plusDays(7))));

        Response<Integer> createResponse = Client.createClient(addedClientRequest);
        newClientId = createResponse.body();
        assertBadRequest(createResponse);
        assertBodyNotNull(createResponse);
    }

    @Test(description = "ID 0004 - Create client with the birth date set to a future date")
    public void createClientWithBirthDateSetToFutureDateTest() throws IOException {
        ClientRequest addedClientRequest = newPositiveClient();
        addedClientRequest.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").format(Date.valueOf(LocalDate.now().plusDays(1))));

        Response<Integer> createResponse = Client.createClient(addedClientRequest);
        newClientId = createResponse.body();
        assertCreated(createResponse);
        assertBodyNotNull(createResponse);
        //newClientId = createResponse.body();

        Response<ClientResponse> getResponse = Client.getClientByid(newClientId);
        assertBadRequest(getResponse);

        ErrorResponse expectedResponse = errorClientInvalidBirthDate();
        assertErrorResponse(getErrorResponse(createResponse), expectedResponse);
    }

    @Test(description = "ID 0005 - Create client with the birth date set to after of client date achei que ia passar ")
    public void createClientWithBirthDateAfterClientDate(){
        ClientRequest addedClientRequest = newPositiveClient();
        addedClientRequest.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").format(Date.valueOf(LocalDate.now().plusDays(1))));
        addedClientRequest.setClientDate(new SimpleDateFormat("yyyy-MM-dd").format(Date.valueOf(LocalDate.now())));

        Response<Integer> createResponse = Client.createClient(addedClientRequest);
        newClientId = createResponse.body();
        assertBadRequest(createResponse);
        assertBodyNotNull(createResponse);
        //newClientId = createResponse.body();
    }

    @Test(description = "ID 0006 - Create client with duplicated NIF")
    public void createClientDuplicatedNIF() throws IOException {
        ClientRequest createClientRequest = newPositiveClient();

        Response<Integer> createResponse = Client.createClient(createClientRequest);
        assertCreated(createResponse);
        newClientId = createResponse.body();

        createResponse = Client.createClient(createClientRequest);
        newClientIdHelper = createResponse.body();
        assertBadRequest(createResponse);
        //newClientIdHelper = createResponse.body();
    }

    @Test(description = "ID 0007 - Create client with id")
    public void createClientWithIDTest() throws IOException {
        ClientRequest createClientRequest = newPositiveClient();
        createClientRequest.setId(2);

        Response<Integer> createResponse = Client.createClient(createClientRequest);
        assertBadRequest(createResponse);
        newClientId = createResponse.body();
    }

    @Test(description = "ID 0008 - Create client with Invalid postal code")
    public void createClientWithInvalidPostalCode() throws IOException {
        ClientRequest createClientRequest = newPositiveClient();
        createClientRequest.setPostalCode("38651");

        Response<Integer> createResponse = Client.createClient(createClientRequest);
        assertBadRequest(createResponse);
        newClientId = createResponse.body();
    }

    @Test(description = "ID 0009 - Create Client with invlaid phone number")
    public void createClientWithInvalidPhoneNumber() throws IOException {
        ClientRequest createClientRequest = newPositiveClient();
        createClientRequest.setPhoneNumber(91356180);

        Response<Integer> createResponse = Client.createClient(createClientRequest);
        assertBadRequest(createResponse);
        newClientId = createResponse.body();
    }


}