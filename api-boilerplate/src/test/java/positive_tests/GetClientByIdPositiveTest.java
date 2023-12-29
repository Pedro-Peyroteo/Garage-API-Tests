package positive_tests;

import api.mappings.client.Client;
import org.testng.annotations.Test;
import retrofit2.Response;

import static api.retrofit.projeto_final.Clients.getClientByid;
import static api.validators.ResponseValidator.assertOk;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class GetClientByIdPositiveTest
{
    @Test(description = "Get client by id :) ")
    public void getClientByIdTest()
    {
        Response<Client> response = getClientByid(1);
        assertOk(response);
        assertThat("Body is not null", response.body(), notNullValue());

        Client clientResponse = response.body();
        assertThat("name is not the expect",clientResponse.getFirstName(), is("Afonso"));
    }
}
