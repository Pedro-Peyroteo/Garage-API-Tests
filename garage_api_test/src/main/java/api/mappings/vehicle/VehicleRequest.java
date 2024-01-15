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
public class VehicleRequest {
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
