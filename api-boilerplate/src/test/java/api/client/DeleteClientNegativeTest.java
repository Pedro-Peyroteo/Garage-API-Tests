package api.client;

import static api.validators.ResponseBodyValidator.assertBodyNotNull;
import static api.validators.ResponseValidator.assertNoContent;
import static api.validators.ResponseValidator.assertNotFound;
import static api.validators.ResponseValidator.assertOk;

import java.util.List;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import api.mappings.client.ClientResponse;
import api.retrofit.garage.Client;
import retrofit2.Response;

public class DeleteClientNegativeTest {
    private Integer newClientId;



    @Test(description = "Delete client with negative ID")
    public void deleteClientWithValidID() {
        
        newClientId = -1; 

        // Tenta excluir o cliente com o ID especificado
        Response<Void> deleteResponse = Client.deleteClient(newClientId);

        // Verifica se a exclusão foi bem-sucedida
        assertNoContent(deleteResponse);
    }
    @Test(description = "Get a non-existent client by id")
    public void getNonExistentClientByIdTest() {
    // Obtém todos os clientes para determinar o último ID existente
    Response<List<ClientResponse>> getResponse = Client.getAllClients();
    assertOk(getResponse);
    assertBodyNotNull(getResponse);

    // Gera um ID não existente
    ClientResponse lastClient = getResponse.body().get(getResponse.body().size() - 1);
    Integer testId = lastClient.getId() + 1;

    // Tenta obter um cliente com o ID não existente
    Response<ClientResponse> response = Client.getClientByid(testId);

    // Verifica se a resposta é um Not Found (404)
    assertNotFound(response);

    // Verifica a mensagem de erro (assumindo que sua implementação inclui uma mensagem no corpo da resposta)
    assertErrorMessage(response, "Cliente não encontrado com o ID: " + testId);
}
    private void assertErrorMessage(Response<?> response, String expectedErrorMessage) {
        // Verifica se o corpo da resposta não é nulo
        assertNotNull(response.body());

        // Adapte conforme necessário para a estrutura da sua resposta
        String actualErrorMessage = (String) response.body();
        assertEquals(actualErrorMessage, expectedErrorMessage, "Mensagem de erro incorreta");
    }
    private void assertEquals(String actualErrorMessage, String expectedErrorMessage, String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'assertEquals'");
    }
    private void assertNotNull(Object body) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'assertNotNull'");
    }
}
