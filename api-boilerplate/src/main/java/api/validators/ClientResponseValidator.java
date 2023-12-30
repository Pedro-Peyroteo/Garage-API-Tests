package api.validators;

import api.mappings.client.ClientResponse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ClientResponseValidator {

    public static void assertClientResponse(ClientResponse clientResponse, ClientResponse expected){
        assertThat("Response should not be null", clientResponse, notNullValue());
        assertThat("First name not expected", clientResponse, is(expected.getFirstName()));
        assertThat("Last name not the expected", clientResponse, is(expected.getLastName()));
        assertThat("Address not the expected", clientResponse, is(expected.getAddress()));
        assertThat("Postal Code not the expected", clientResponse, is(expected.getPostalCode()));
        assertThat("City not the expected", clientResponse, is(expected.getCity()));
        assertThat("Country not the expected", clientResponse, is(expected.getCountry()));
        assertThat("Phone Number not the expected", clientResponse, is(expected.getPhoneNumber()));
        assertThat("NIF not the expected", clientResponse, is(expected.getNif()));
        assertThat("Birth Date not the expected", clientResponse, is(expected.getBirthDate()));
        assertThat("Client Date not the expected", clientResponse, is(expected.getClientDate()));
        assertThat("ID not the expected", clientResponse, is(expected.getId()));

    }   
    // TODO: ACABAR
    private static boolean isValidNIF(Integer nif){
        String nifStr = Integer.toString(nif);
        final int max=9;
        int checkSum=0;

        //check if is numeric and has 9 numbers
        if (!nifStr.matches("[0-9]+") || nifStr.length()!=max)
            return false;

        //calculate checkSum
        for (int i=0; i<max-1; i++){
            checkSum+=(nifStr.charAt(i)-'0')*(max-i);
        }

        int checkDigit= 11-(checkSum % 11);
        //if checkDigit is higher than 9 set it to zero
        if (checkDigit>9) checkDigit=0;
        //compare checkDigit with the last number of NIF
        return checkDigit==nifStr.charAt(max-1)-'0';
    }
}
