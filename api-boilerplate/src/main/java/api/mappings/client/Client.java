package api.mappings.client;

import api.mappings.vehicle.Vehicle;
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
@Builder
public class Client {
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
       private Date birthDate;
       @JsonProperty
       private Date clientDate;
       @JsonProperty
       private List<Vehicle> vehicles;
}