package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

// BEGIN
@AllArgsConstructor
@Getter
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    public String serialize() {
        ObjectMapper mapper = new ObjectMapper();
        String result = null;
        try {
            result = mapper.writeValueAsString(this);
        } catch(JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public static Car unserialize(String json) {
        ObjectMapper mapper = new ObjectMapper();
        Car car = null;
        try {
            car = mapper.readValue(json, Car.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return car;
    }
    // END
}
