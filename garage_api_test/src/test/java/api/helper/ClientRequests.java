package api.helper;

import api.mappings.client.ClientRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Function to create a new client, when called.
 */
public class ClientRequests {
    // Para usar em casos de teste positivos
    public static ClientRequest newPositiveClient(){
        return ClientRequest.builder()
                .firstName("Pedro")
                .lastName("Peyroteo")
                .address("Rua do Vale, 50")
                .postalCode("3865-100")
                .city("Estarreja")
                .country("Portugal")
                .phoneNumber(910356180)
                .nif(275713741)
                .birthDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())))
                .clientDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())))
                .build();
    }

    // Para usar em casos de teste negativos
    public static ClientRequest newNegativeClient(){
        return ClientRequest.builder()
                .firstName("Pedro")
                .lastName("Peyroteo")
                .address("Rua do Vale, 50")
                .postalCode("3865-100")
                .city("Estarreja")
                .country("Portugal")
                .phoneNumber(910356180)
                .nif(275713741)
                .birthDate("2000-07-25")
                .clientDate("2024-01-01")
                .build();
    }
}
