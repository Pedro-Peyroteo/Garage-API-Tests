package api.client;

import api.mappings.client.ClientRequest;
import api.mappings.client.ClientResponse;
import api.retrofit.garage.Client;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import static api.helper.ClientRequests.newPositiveClient;
import static api.validators.ClientValidator.assertClientResponse;
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

    @Test(description = "Create client with null fields")
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




    @Test(description = "Create client with the birth date set to a past day")
    public void createClientWithBirthDateLessThanToday(){
        ClientRequest addedClientRequest = newPositiveClient();
        addedClientRequest.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").format(Date.valueOf(LocalDate.now().minusDays(1))));

        Response<Integer> createResponse = Client.createClient(addedClientRequest);
        assertCreated(createResponse);
        assertBodyNotNull(createResponse);
        newClientId = createResponse.body();

        Response<ClientResponse> getResponse = Client.getClientByid(newClientId);
        assertBadRequest(getResponse);
    }

    @Test(description = "Create client with the client date set to future date")
    public void createClientWithClientDateSetToFutureDateTest(){
        ClientRequest addedClientRequest = newPositiveClient();
        addedClientRequest.setClientDate(new SimpleDateFormat("yyyy-MM-dd").format(Date.valueOf(LocalDate.now().plusDays(7))));

        Response<Integer> createResponse = Client.createClient(addedClientRequest);
        assertCreated(createResponse);
        assertBodyNotNull(createResponse);
        newClientId = createResponse.body();

        Response<ClientResponse> getResponse = Client.getClientByid(newClientId);
        assertBadRequest(getResponse);;
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
        assertBadRequest(getResponse);
    }
}
