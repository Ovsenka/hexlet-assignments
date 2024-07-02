package exercise;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> apartments, int countApartments) {
        return apartments.stream()
                .sorted(Comparator.comparing(apartment -> apartment.getArea()))
                .limit(countApartments)
                .map(a -> a.toString())
                .toList();
    }
}
// END
