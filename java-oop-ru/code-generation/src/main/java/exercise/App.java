package exercise;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
class App {
    public static void save(Path filepath, Car car) {
        try {
            Files.deleteIfExists(filepath);
            Files.createFile(filepath);
            Files.writeString(filepath, car.serialize());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Car extract(Path filepath) {
        String json = null;
        try {
            json = Files.readString(filepath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Car.unserialize(json);
    }
}


// END
