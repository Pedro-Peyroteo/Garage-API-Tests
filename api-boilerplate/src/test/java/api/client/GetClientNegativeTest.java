package api.client;

import api.mappings.client.ClientResponse;
import org.testng.annotations.Test;
import retrofit2.Response;

import static api.retrofit.garage.Client.getClientByid;
import static api.validators.ResponseValidator.assertNotFound;
import static org.testng.AssertJUnit.assertNull;

public class GetClientNegativeTest
{

    @Test(description = "Get a non-existent client by id")
    public void getNonExistentClientByIdTest() {
        Response<ClientResponse> response = getClientByid(-1);
        assertNotFound(response);
        assertNull("Body should be null for non-existent client", response.body());
    }
}
