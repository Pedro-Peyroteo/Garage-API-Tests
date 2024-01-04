package api.client;

import api.helper.ClientRequests;
import api.mappings.client.ClientRequest;
import api.mappings.client.ClientResponse;
import api.retrofit.garage.Client;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.time.LocalDate;

import static api.helper.ClientRequests.newPositiveClient;
import static api.retrofit.garage.Client.createClient;
import static api.validators.ResponseBodyValidator.assertBodyNotNull;
import static api.validators.ResponseValidator.assertCreated;
import static api.validators.ResponseValidator.assertNoContent;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CreateClientPositiveTest {
    private Integer newClientId;


    @AfterMethod
    public void cleanUp(){
        Response<Void> response = Client.deleteClient(newClientId);
        assertNoContent(response);

        newClientId = null;
    }

    @Test(description = "create client with success")
    public void createClientTest() {
        // Dados do cliente a serem enviados na requisição
        ClientRequest clientRequest = newPositiveClient();

        Response<Integer> response = createClient(clientRequest);
        assertCreated(response);
        newClientId = response.body();

        assertThat("Body is not null", response.body(), notNullValue());
    }




}
