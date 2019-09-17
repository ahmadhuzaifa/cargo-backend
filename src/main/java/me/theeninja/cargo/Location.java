package me.theeninja.cargo;

import com.smartcar.sdk.data.VehicleLocation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Data
public class Location {
    private double x;
    private double y;
}
