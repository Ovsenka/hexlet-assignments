package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class Validator {
    public static List<String> validate(Object object) {
        List<String> invalidFields = new ArrayList<>();
        try {
            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(NotNull.class)) {
                    if (field.get(object) == null) {
                        invalidFields.add(field.getName());
                    }
                }
            }
        } catch (IllegalAccessException exception) {
            exception.printStackTrace();
        }
        return invalidFields;
    }

    public static Map<String, List<String>> advancedValidate(Object object) throws IllegalAccessException{
        HashMap<String, List<String>> map = new HashMap<>();
        try {
            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                List<String> listErrors = new ArrayList<>();
                if (field.isAnnotationPresent(NotNull.class) && field.get(object) == null) {
                    listErrors.add("can not be null");
                }
                if (field.isAnnotationPresent(MinLength.class)) {
                    Object objectValue = field.get(object);
                    String value = (String) objectValue;
                    int minLength = field.getDeclaredAnnotation(MinLength.class).minLength();
                    if (value != null && value.length() < minLength ){
                        listErrors.add("length less than %s".formatted(minLength));
                    }
                }
                if (!listErrors.isEmpty()) {
                    map.put(field.getName(), listErrors);
                }
            }
        } catch (IllegalAccessException exception) {
            exception.printStackTrace();
        }
        return map;
    }

}
// END
