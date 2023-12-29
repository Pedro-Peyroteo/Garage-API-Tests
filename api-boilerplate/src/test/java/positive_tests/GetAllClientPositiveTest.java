package positive_tests;

import api.mappings.client.Client;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.util.List;

import static api.retrofit.projeto_final.Clients.getAllClient;
import static api.validators.ResponseValidator.assertOk;

public class GetAllClientPositiveTest {
    @Test(description = "Get all clients")
    public void getAllClientTest(){
        Response<List<Client>> response = getAllClient();
        assertOk(response);
    }
}
