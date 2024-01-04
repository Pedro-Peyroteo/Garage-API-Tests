package api.client;

import api.mappings.client.ClientRequest;
import api.mappings.client.ClientResponse;
import api.retrofit.garage.Client;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import retrofit2.Response;
import java.util.List;
import static api.retrofit.garage.Client.getAllClients;
import static api.validators.ListValidator.assertListHasSize;
import static api.validators.ResponseBodyValidator.assertBodyNotNull;
import static api.validators.ListValidator.assertListNotEmpty;
import static api.validators.ClientValidator.assertClientResponse;
import static api.validators.ResponseBodyValidator.assertId;
import static api.validators.ResponseValidator.*;
import static api.helper.ClientRequests.newPositiveClient;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

public class GetClientPositiveTest {
    private Integer newClientId;
    private ClientRequest newClientRequest;


    @BeforeMethod
    public void setUp(){
        newClientRequest = newPositiveClient();

        Response<Integer> response = Client.createClient(newClientRequest);
        assertCreated(response);
        newClientId = response.body();
    }

    @AfterMethod
    public void cleanUp(){
        Response<Void> response = Client.deleteClient(newClientId);
        assertNoContent(response);

        newClientId = null;
        newClientRequest = null;
    }


    @Test(description = "Get all clients")
    public void getAllClientTest(){
        Response<List<ClientResponse>> response = getAllClients();
        assertOk(response);

        assertBodyNotNull(response);
        assertListNotEmpty(response.body());
        assertListHasSize(response.body(), greaterThanOrEqualTo(1));
    }

    @Test(description = "Get client by id :) ")
    public void getClientByIdTest()
    {
        Response<ClientResponse> response = Client.getClientByid(newClientId);
        assertOk(response);

        assertBodyNotNull(response);
        assertClientResponse(response.body(), newClientRequest);
        assertId(response.body().getId(), newClientId);

    }
}
