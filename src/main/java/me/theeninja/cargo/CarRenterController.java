package me.theeninja.cargo;

import com.smartcar.sdk.AuthClient;
import com.smartcar.sdk.SmartcarException;
import com.smartcar.sdk.Vehicle;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
public class CarRenterController {
    private static final String clientId = "65b7acd2-6827-4876-b5e7-9710d93d312d";
    private static final String clientSecret = "d9b36039-3532-45cb-ad97-26292f6426da";
    private static final String redirectUri = "http://localhost:8000/exchange";

    private static final String[] GET_AVAILABLE_CARS_REQUIRED_PERMISSIONS = {
        "required:read_vehicle_info",
        "required:read_vin",
        "required:control_security",
        "required:read_location"
    };

    private static final AuthClient AUTH_CLIENT = new AuthClient(
        clientId,
        clientSecret,
        redirectUri,
        GET_AVAILABLE_CARS_REQUIRED_PERMISSIONS,
        false
    );


    @GetMapping("/query-available-cars")
    public String queryAvailableCars(final @RequestParam(name = "userLocation") Location userLocation, final @RequestParam(name = "radius") int radius) {
        val authURL = AUTH_CLIENT.new AuthUrlBuilder().build();

        return "redirect:/get-available-cars";
    }

    @GetMapping("/get-available-cars")
    public Iterable<Vehicle> getAvailableCars(final @RequestParam(name = "code") String code) throws SmartcarException {
        val auth = AUTH_CLIENT.exchangeCode(code);
        val accessToken = auth.getAccessToken();

        val vehicleIDsResponse = AuthClient.getVehicleIds(accessToken);
        val vehicleIDsData = vehicleIDsResponse.getData();
        val vehicleIDs = vehicleIDsData.getVehicleIds();

        return Arrays.stream(vehicleIDs).map(vehicleID -> new Vehicle(vehicleID, accessToken)).collect(Collectors.toUnmodifiableSet());
    }
}
