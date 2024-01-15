package api.validators;

import api.mappings.client.ClientRequest;
import api.mappings.client.ClientResponse;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static api.validators.ListValidator.assertListHasSize;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ClientValidator {
    public static void assertClientResponse(ClientResponse clientResponse, ClientRequest expected){
        assertThat("Response should not be null", clientResponse, notNullValue());

        // Não se consegue saber o ID esperado pois este não é passado no body do create
        /*assertThat("ID should not be null", clientResponse.getId(), notNullValue());
        assertThat("ID not the expected", clientResponse.getId(), is(expected.getId()));*/

        assertThat("First name should not be null", clientResponse.getFirstName(), notNullValue());
        assertThat("First name not expected", clientResponse.getFirstName(), is(expected.getFirstName()));

        assertThat("Last name should not be null", clientResponse.getLastName(), notNullValue());
        assertThat("Last name not the expected", clientResponse.getLastName(), is(expected.getLastName()));

        assertThat("Address should not be null", clientResponse.getAddress(), notNullValue());
        assertThat("Address not the expected", clientResponse.getAddress(), is(expected.getAddress()));

        assertThat("Postal code should not be null", clientResponse.getPostalCode(), notNullValue());
        assertThat("Postal Code not the expected", clientResponse.getPostalCode(), is(expected.getPostalCode()));

        assertThat("City should not be null", clientResponse.getCity(), notNullValue());
        assertThat("City not the expected", clientResponse.getCity(), is(expected.getCity()));

        assertThat("Country code should not be null", clientResponse.getCountry(), notNullValue());
        assertThat("Country not the expected", clientResponse.getCountry(), is(expected.getCountry()));

        assertThat("Phone number should not be null", clientResponse.getPhoneNumber(), notNullValue());
        Matcher phoneNumberPattern = Pattern.compile("^([0-9]{9})$").matcher(String.valueOf(clientResponse.getPhoneNumber()));
        assertThat("Phone Number not the expected", clientResponse.getPhoneNumber(), is(expected.getPhoneNumber()));
        assertThat("Phone number is invalid", phoneNumberPattern.matches());

        assertThat("NIF should not be null", clientResponse.getNif(), notNullValue());
        Integer clientNIF = clientResponse.getNif();
        assertThat("NIF with invalid format ", isValidNIF(clientNIF), is(true));
        assertThat("NIF not the expected", clientNIF, is(expected.getNif()));

        assertThat("Birth date should not be null", clientResponse.getBirthDate(), notNullValue());
        assertThat("Birth Date not the expected", clientResponse.getBirthDate(), is(expected.getBirthDate())); //
        assertThat("Birth date is invalid", clientResponse.getBirthDate(), is(greaterThanOrEqualTo(String.valueOf(LocalDate.now()))));

        assertThat("Client date should not be null", clientResponse.getClientDate(), notNullValue());
        assertThat("Client Date not the expected", clientResponse.getClientDate(), is(expected.getClientDate())); //
        assertThat("Client date is invalid", clientResponse.getClientDate(), is(greaterThanOrEqualTo(String.valueOf(LocalDate.now()))));

        //if (clientResponse.getClientDate() != null)
            //assertThat("Client Date is invalid", clientResponse.getClientDate(), is(lessThanOrEqualTo(clientResponse.getClientDate());

        assertListHasSize(clientResponse.getVehicles(), 0);
    }   

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
