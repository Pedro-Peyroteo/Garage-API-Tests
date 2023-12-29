package api.mappings.vehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {
    @JsonProperty
    private Integer id;
    @JsonProperty
    private Integer client; // Assuming this is the reference to the client
    @JsonProperty
    private String brand;
    @JsonProperty
    private String model;
    @JsonProperty
    private Integer plateYear;
    @JsonProperty
    private String type;
    @JsonProperty
    private String plate;
    @JsonProperty
    private boolean active;
}
