package api.client;

import api.mappings.garage.client.ClientResponse;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.util.List;

import static api.retrofit.garage.Client.getAllClients;
import static api.retrofit.garage.Client.getClientByid;
import static api.validators.ListValidator.assertListHasSize;
import static api.validators.ResponseBodyValidator.assertBodyNotNull;
import static api.validators.ListValidator.assertListNotEmpty;
import static api.validators.ResponseValidator.assertOk;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

public class GetClientPositiveTest {

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
        Response<ClientResponse> response = getClientByid(1);
        assertOk(response);
        assertThat("Body is not null", response.body(), notNullValue());

        ClientResponse clientResponse = response.body();
        assertThat("name is not the expect",clientResponse.getFirstName(), is("Afonso"));

    }
}
