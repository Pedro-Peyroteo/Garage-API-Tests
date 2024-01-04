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
        assertThat("First name not expected", clientResponse.getFirstName(), is(expected.getFirstName()));
        assertThat("Last name not the expected", clientResponse.getLastName(), is(expected.getLastName()));
        assertThat("Address not the expected", clientResponse.getAddress(), is(expected.getAddress()));
        assertThat("Postal Code not the expected", clientResponse.getPostalCode(), is(expected.getPostalCode()));
        assertThat("City not the expected", clientResponse.getCity(), is(expected.getCity()));
        assertThat("Country not the expected", clientResponse.getCountry(), is(expected.getCountry()));

        Matcher phoneNumberPattern = Pattern.compile("^([0-9]{9})$").matcher(String.valueOf(clientResponse.getPhoneNumber()));
        assertThat("Phone Number not the expected", clientResponse.getPhoneNumber(), is(expected.getPhoneNumber()));
        assertThat("Phone number is invalid", phoneNumberPattern.matches());

        Integer clientNIF = clientResponse.getNif();
        assertThat("NIF with invalid format ", isValidNIF(clientNIF), is(true));
        assertThat("NIF not the expected", clientNIF, is(expected.getNif()));



        assertThat("Birth Date not the expected", clientResponse.getBirthDate(), is(expected.getBirthDate())); //
        if (clientResponse.getBirthDate() != null)
            assertThat("Birth date is invalid", clientResponse.getBirthDate(), is(lessThanOrEqualTo(String.valueOf(LocalDate.now()))));

        assertThat("Client Date not the expected", clientResponse.getClientDate(), is(expected.getClientDate())); //
        if (clientResponse.getClientDate() != null)
            assertThat("Birth date is invalid", clientResponse.getClientDate(), is(lessThanOrEqualTo(String.valueOf(LocalDate.now()))));

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
