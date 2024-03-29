package edu.kocaeli.actofgod_api.dto.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LocationDto {
    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
    private Long capacity;
    private String districtName;
}
