package spring.boot.samples.k8s.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemperatureConversionController {

    private static final double ZERO_CELSIUS_FAHRENHEIT = -17.77778;
    
    @RequestMapping("/temperature/c/f/{degrees}")
    public String convertCelsiusToFahrenheit(@PathVariable(value = "degrees", required = true) Double degrees) {
        return String.valueOf(degrees * 9 / 5 + 32);
    }

    @RequestMapping("/temperature/f/c/{degrees}")
    public String convertFahrenheitToCelsius(@PathVariable(value = "degrees", required = true) Double degrees) {
        if(degrees == 0) {
            return String.valueOf(ZERO_CELSIUS_FAHRENHEIT);
        }
        return String.valueOf((degrees - 32) * 5/9);
    }
    
}
