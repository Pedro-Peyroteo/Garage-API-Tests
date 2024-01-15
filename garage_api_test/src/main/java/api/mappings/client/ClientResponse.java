package api.mappings.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ClientResponse {
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
       @JsonProperty
       private List<api.mappings.vehicle.VehicleResponse> vehicles;
}