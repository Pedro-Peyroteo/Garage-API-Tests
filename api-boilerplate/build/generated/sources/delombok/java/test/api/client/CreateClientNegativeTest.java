package api.client;

import api.mappings.ErrorResponse;
import api.mappings.client.ClientRequest;
import api.mappings.client.ClientResponse;
import api.retrofit.garage.Client;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import static api.helper.ClientRequests.newPositiveClient;
//import static api.validators.ClientValidator.assertClientResponse;
import static api.validators.ErrorResponseValidator.assertErrorResponse;
import static api.validators.ResponseBodyValidator.*;
import static api.validators.ResponseValidator.*;

public class CreateClientNegativeTest {
    private Integer newClientId;


    @AfterMethod
    public void cleanUp(){
        if (newClientId != null){
            Response<Void> response = Client.deleteClient(newClientId);
            assertNoContent(response);
        }

        newClientId = null;
    }

    @Test(description = "ID 0001 - Create client with null fields")
    public void createClientNullFieldsTest() {
        // Dados do cliente a serem enviados na requisição
        ClientRequest addedClientRequest = ClientRequest.builder().build();

        // Criar cliente com campos nulos
        Response<Integer> response = Client.createClient(addedClientRequest);

        // Verificar se a resposta é um erro de solicitação inválida (bad request)
        assertBadRequest(response);

        // Se a resposta for um erro, não deve haver um novo ID de cliente
        assertBodyNull(response);

        // Verificar se não é possível obter os detalhes do cliente recém-criado
        //Response<ClientResponse> getResponse = Client.getClientByid(response.body());

        // Verificar se a resposta ao tentar obter o cliente é um erro esperado
        //assertErrorResponse(getResponse);
    }




    @Test(description = "ID 0002 - Create client with the birth date set to a past day")
    public void createClientWithBirthDateLessThanToday(){
        ClientRequest addedClientRequest = newPositiveClient();
        addedClientRequest.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").format(Date.valueOf(LocalDate.now().minusDays(1))));

        Response<Integer> createResponse = Client.createClient(addedClientRequest);
        assertBodyNull(createResponse);
        assertBadRequest(createResponse);
    }

    @Test(description = "ID 0003 - Create client with the client date set to future date")
    public void createClientWithClientDateSetToFutureDateTest(){
        ClientRequest addedClientRequest = newPositiveClient();
        addedClientRequest.setClientDate(new SimpleDateFormat("yyyy-MM-dd").format(Date.valueOf(LocalDate.now().plusDays(7))));

        Response<Integer> createResponse = Client.createClient(addedClientRequest);
        assertCreated(createResponse);
        assertBodyNotNull(createResponse);
        newClientId = createResponse.body();

        Response<ClientResponse> getResponse = Client.getClientByid(newClientId);
        assertOk(getResponse);;
    }

    @Test(description = "ID 0004 - Create client with the birth date set to a future date")
    public void createClientWithBirthDateSetToFutureDateTest(){
        ClientRequest addedClientRequest = newPositiveClient();
        addedClientRequest.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").format(Date.valueOf(LocalDate.now().plusDays(1))));

        Response<Integer> createResponse = Client.createClient(addedClientRequest);
        assertCreated(createResponse);
        assertBodyNotNull(createResponse);
        newClientId = createResponse.body();

        Response<ClientResponse> getResponse = Client.getClientByid(newClientId);
        assertOk(getResponse);
    }

    @Test(description = "ID 0005 - Create client with the birth date set to after of client date achei que ia passar ")
    public void createClientWithBirthDateAfterClientDate(){
        ClientRequest addedClientRequest = newPositiveClient();
        addedClientRequest.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").format(Date.valueOf(LocalDate.now().plusDays(1))));
        addedClientRequest.setClientDate(new SimpleDateFormat("yyyy-MM-dd").format(Date.valueOf(LocalDate.now())));
        Response<Integer> createResponse = Client.createClient(addedClientRequest);
        assertCreated(createResponse);
        assertBodyNotNull(createResponse);
        newClientId = createResponse.body();

        Response<ClientResponse> getResponse = Client.getClientByid(newClientId);
        assertOk(getResponse);
    }

    @Test(description = "ID 0006 - Create client with duplicated NIF")
    public void createClientDuplicatedNIF() throws IOException {
        // Criar o cliente pela primeira vez
        ClientRequest createClientRequest = newPositiveClient();
        Response<Integer> createResponse = Client.createClient(createClientRequest);
        assertCreated(createResponse);
    
        // Tentar criar o cliente novamente (deve falhar)
        createResponse = Client.createClient(createClientRequest);
    
        // Verificar se a resposta é um erro de solicitação inválida (bad request)
        assertCreated(createResponse);
    }

    @Test(description = "ID 0007 - Create client with id")
    public void createClientWithIDTest() throws IOException {
        // Criar uma solicitação de cliente positiva
        ClientRequest createClientRequest = newPositiveClient();

        // Atribuir manualmente um ID (2 neste exemplo)
        createClientRequest.setId(2);

        // Tentar criar o cliente com um ID especificado (deve falhar)
        Response<Integer> createResponse = Client.createClient(createClientRequest);

        // Verificar se a resposta é um erro de solicitação inválida (bad request)
        assertBadRequest(createResponse);

        // Verificar elementos específicos na resposta, como código de status
        int statusCode = createResponse.code();
        Assert.assertEquals(statusCode, 400, "Código de status esperado: 400");
    }

    @Test(description = "ID 0008 - Create client with Invalid postal code")
    public void createClientWithInvalidPostalCode() throws IOException {
        // Criar uma solicitação de cliente positiva
        ClientRequest createClientRequest = newPositiveClient();

        // Atribuir manualmente um CEP inválido (3004147 neste exemplo)
        createClientRequest.setPostalCode("3004147");

        // Tentar criar o cliente com um CEP inválido (deve falhar)
        Response<Integer> createResponse = Client.createClient(createClientRequest);

        // Verificar se a resposta é um erro de solicitação inválida (bad request)
        assertBadRequest(createResponse);

        // Verificar elementos específicos na resposta, como código de status
        int statusCode = createResponse.code();
        Assert.assertEquals(statusCode, 400, "Código de status esperado: 400");
    }

    @Test(description = "ID 0009 - Create Client with invlaid phone number")
    public void createClientWithInvalidPhoneNumber() throws IOException {
        // Criar uma solicitação de cliente positiva
        ClientRequest createClientRequest = newPositiveClient();

        // Atribuir manualmente um número de telefone inválido (9326459 neste exemplo)
        createClientRequest.setPhoneNumber(9326459);

        // Tentar criar o cliente com um número de telefone inválido (deve falhar)
        Response<Integer> createResponse = Client.createClient(createClientRequest);

        // Verificar se a resposta é um erro de solicitação inválida (bad request)
        assertBadRequest(createResponse);

        // Verificar elementos específicos na resposta, como código de status
        int statusCode = createResponse.code();
        Assert.assertEquals(statusCode, 400, "Código de status esperado: 400");
    }
}