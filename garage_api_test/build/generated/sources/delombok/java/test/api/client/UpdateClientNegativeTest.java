package api.client;

import api.mappings.ErrorResponse;
import api.mappings.client.ClientRequest;
import api.retrofit.garage.Client;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import static api.helper.ClientRequests.newPositiveClient;
import static api.helper.ErrorClientResponses.*;
import static api.validators.ErrorResponseValidator.assertErrorResponse;
import static api.retrofit.garage.Error.getErrorResponse;
import static api.validators.ResponseValidator.*;

public class UpdateClientNegativeTest {
    private Integer unexpectedCreatedClientID;
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

    @Test(description = "ID  0023 - Update non Existent client")
    public void updateNonExistentClientTest() throws IOException {
        Integer clientTestId = newClientId;

        Response<Void> deleteResponse = Client.deleteClient(clientTestId);
        assertNoContent(deleteResponse);

        Response<Integer> updateResponse = Client.updateClient(newClientId, newClientRequest);
        assertNotFound(updateResponse);

        ErrorResponse expected = errorClientNotFound(clientTestId);
        assertErrorResponse(getErrorResponse(updateResponse), expected);
    }

    @Test(description = "ID  0024 - Update client with id 0")
    public void updateClientWithIdZero() throws Exception{
        ClientRequest updatedClientRequest = newClientRequest;
        Integer clientIDToTest = 0;

        Response<Integer> updateResponse = Client.updateClient(clientIDToTest, updatedClientRequest);
        assertBadRequest(updateResponse);

        ErrorResponse expected = errorClientInvalidID(clientIDToTest);
        assertErrorResponse(getErrorResponse(updateResponse), expected);
    }

    @Test(description = "ID 0025 - Update client with negative id")
    public void updateClientWithNegativeIdTest() throws Exception{
        ClientRequest updatedClientRequest = newClientRequest;
        Integer clientIDToTest = -1;

        Response<Integer> updateResponse = Client.updateClient(clientIDToTest, updatedClientRequest);
        assertBadRequest(updateResponse);

        ErrorResponse expectedResponse = errorClientInvalidID(clientIDToTest);
        assertErrorResponse(getErrorResponse(updateResponse), expectedResponse);
    }

    @Test(description = "ID 0026 - Update client with null fields")
    public void updateClientNullFieldsTest() throws IOException {
        ClientRequest updatedClientRequest = ClientRequest.builder().build();

        Response<Integer> updateResponse = Client.updateClient(newClientId, updatedClientRequest);
        assertBadRequest(updateResponse);

        ErrorResponse expectedResponse = errorClientInvalidBodyByID(newClientId);
        assertErrorResponse(getErrorResponse(updateResponse), expectedResponse);
    }

    @Test(description = "ID 0027 - Update client with Duplicated NIF")
    public void updateClientDuplicatedNIFTest() throws IOException {
        Integer clientNIFToTest = 275713741;

        ClientRequest createdClientRequest = newPositiveClient();
        createdClientRequest.setNif(clientNIFToTest);

        Response<Integer> createResponse = Client.createClient(createdClientRequest);
        unexpectedCreatedClientID = createResponse.body();
        assertCreated(createResponse);

        ClientRequest updatedClientRequest = createdClientRequest;
        updatedClientRequest.setNif(clientNIFToTest);

        Response<Integer> updateResponse = Client.updateClient(newClientId, updatedClientRequest);
        assertBadRequest(updateResponse);

        ErrorResponse expectedResponse = errorClientDuplicatedNIFByID(newClientId);
        assertErrorResponse(getErrorResponse(updateResponse), expectedResponse);
    }

    @Test(description = "ID 0028 - Update client with birth date with current date")
    public void updateClientWithBirthDateWithCurrentDateTest() throws IOException {
        ClientRequest updatedClientRequest = newClientRequest;
        updatedClientRequest.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").format(Date.valueOf(LocalDate.now().plusDays(4))));

        Response<Integer> updateResponse = Client.updateClient(newClientId, updatedClientRequest);
        assertBadRequest(updateResponse);

        ErrorResponse expectedResponse = errorClientInvalidBirthDateByID(newClientId);
        assertErrorResponse(getErrorResponse(updateResponse), expectedResponse);
    }

    @Test(description = "ID 0029 - Update client with client date with current date")
    public void updateClientWithClientDateWithCurrentDateTest() throws IOException {
        ClientRequest updatedClientRequest = newClientRequest;
        updatedClientRequest.setClientDate(new SimpleDateFormat("yyyy-MM-dd").format(Date.valueOf(LocalDate.now().plusDays(7))));

        Response<Integer> updateResponse = Client.updateClient(newClientId, updatedClientRequest);
        assertBadRequest(updateResponse);

        ErrorResponse expectedResponse = errorClientInvalidClientDateByID(newClientId);
        assertErrorResponse(getErrorResponse(updateResponse), expectedResponse);
    }

    @Test(description = "ID 0030 - Update client with ID")
    public void updateClientWithIDTest() throws IOException {
        ClientRequest updatedClientRequest = newClientRequest;
        newClientRequest.setId(2);

        Response<Integer> createResponse = Client.updateClient(newClientId, updatedClientRequest);
        assertBadRequest(createResponse);

        ErrorResponse expectedResponse = errorClientInvalidIDBodyBydID(newClientId);
        assertErrorResponse(getErrorResponse(createResponse), expectedResponse);
    }

    @Test(description = "ID 0031 - Update Client with invalid Postal Code ")
    public void updateClientWithInvalidPostalCode() throws IOException {
        ClientRequest updatedClientRequest = newClientRequest;
        newClientRequest.setPostalCode("3765122");

        Response<Integer> createResponse = Client.updateClient(newClientId, updatedClientRequest);
        assertBadRequest(createResponse);

        ErrorResponse expectedResponse = errorClientInvalidPostalCodeByID(newClientId);
        assertErrorResponse(getErrorResponse(createResponse), expectedResponse);
    }

    @Test(description = "ID 0032 - Update client with invalid phone number")
    public void updateClientWithInvalidPhoneNumber() throws IOException {
        ClientRequest updatedClientRequest = newClientRequest;
        newClientRequest.setPhoneNumber(91356180);

        Response<Integer> createResponse = Client.updateClient(newClientId, updatedClientRequest);
        assertBadRequest(createResponse);

        ErrorResponse expectedResponse = errorClientInvalidPhoneNumberByID(newClientId);
        assertErrorResponse(getErrorResponse(createResponse), expectedResponse);
    }
}
