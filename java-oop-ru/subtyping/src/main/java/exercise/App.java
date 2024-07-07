package exercise;

import java.util.Map.Entry;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage KvStorage) {
        for (Entry entry : KvStorage.toMap().entrySet()) {
            String key = entry.getKey().toString();
            String value = entry.getValue().toString();
            KvStorage.unset(key);
            KvStorage.set(value, key);
        }
    }
}
// END
