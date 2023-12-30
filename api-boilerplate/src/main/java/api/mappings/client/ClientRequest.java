package api.mappings.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientRequest {
    @JsonProperty
    private Integer id;
    @JsonProperty
    private String firstName;
    @JsonProperty
    private String lastName;
    @JsonProperty
    private String address;
    @JsonProperty
    private String postalCode;
    @JsonProperty
    private String city;
    @JsonProperty
    private String country;
    @JsonProperty
    private Integer phoneNumber;
    @JsonProperty
    private Integer nif;
    @JsonProperty
    private String birthDate;
    @JsonProperty
    private String clientDate;
}