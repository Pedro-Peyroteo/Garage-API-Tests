package api.client;

import api.mappings.client.ClientRequest;
import api.retrofit.garage.Client;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.time.LocalDate;

import static api.retrofit.garage.Client.createClient;
import static api.validators.ResponseValidator.assertCreated;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CreateClientPositiveTest {

    @Test(description = "create client with success")
    public void createClientTest() {
        // Dados do cliente a serem enviados na requisição
        Client clientRequest = ClientRequest.builder(){
                    .firstname("John")
                    .lastname("Doe")
                    .address("123 Main St")
                    .postalCode("12345")
                    .city("Anytown")
                    .country("CountryName")
                    .phoneNumber(123456789)
                    .nif(245069550)
                    .birthDate("2000-10-20")
                    .clientDate("2023-12-23")
                    .build();

        Response<Client> response = createClient(clientRequest);
        assertCreated(response);

        assertThat("Body is not null", response.body(), notNullValue());
        Client clientResponse = response.body();

        // Realizar asserções para verificar se os dados do cliente criado são os mesmos que foram enviados na requisição
        assertThat("id should not be null", clientResponse.getId(), notNullValue());
        assertThat("First name is not the expected", clientResponse.getFirstName(), is(clientRequest.getFirstName()));
        assertThat("Last name is not the expected", clientResponse.getLastName(), is(clientRequest.getLastName()));
        assertThat("Address is not the expected", clientResponse.getAddress(), is(clientRequest.getAddress()));
        assertThat("Postal code is not the expected", clientResponse.getPostalCode(), is(clientRequest.getPostalCode()));
        assertThat("City is not the expected", clientResponse.getCity(), is(clientRequest.getCity()));
        assertThat("Country is not the expected", clientResponse.getCountry(), is(clientRequest.getCountry()));
        assertThat("Phone number is not the expected", clientResponse.getPhoneNumber(), is(clientRequest.getPhoneNumber()));
        assertThat("NIF is not the expected", clientResponse.getNif(), is(clientRequest.getNif()));
        assertThat("Birth date is not the expected", clientResponse.getBirthDate(), is(clientRequest.getBirthDate()));
        assertThat("Client date is not the expected", clientResponse.getClientDate(), is(clientRequest.getClientDate()));
    }
}
