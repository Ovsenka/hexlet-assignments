package exercise;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



class ValidationTest {

    @Test
    void testValidate() {
        Address address1 = new Address("Russia", "Ufa", "Lenina", "54", null);
        List<String> result1 = Validator.validate(address1);
        List<String> expected1 = List.of();
        assertThat(result1).isEqualTo(expected1);

        Address address2 = new Address(null, "London", "1-st street", "5", "1");
        List<String> result2 = Validator.validate(address2);
        List<String> expected2 = List.of("country");
        assertThat(result2).isEqualTo(expected2);

        Address address3 = new Address("USA", null, null, null, "1");
        List<String> result3 = Validator.validate(address3);
        List<String> expected3 = List.of("city", "street", "houseNumber");
        assertThat(result3).isEqualTo(expected3);
    }

    // BEGIN
    @Test
    void testAdvancedValidate() {
        Address address1 = new Address("Ru", "Ufa", "Lenina", "54", null);
        Map<String, List<String>> result1 = null;
        try {
            result1 = Validator.advancedValidate(address1);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        Map<String, List<String>> expected1 = new HashMap<>();
        expected1.put("country", List.of("length less than 3"));
        assertThat(result1).isEqualTo(expected1);

        Address address2 = new Address(null, "Uf", "Lenina", "54", null);
        Map<String, List<String>> result2 = null;
        try {
            result2 = Validator.advancedValidate(address2);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        Map<String, List<String>> expected2 = new HashMap<>();
        expected2.put("country", List.of("can not be null"));
        expected2.put("city", List.of("length less than 3"));
        assertThat(result2).isEqualTo(expected2);
    }
    // END
}
