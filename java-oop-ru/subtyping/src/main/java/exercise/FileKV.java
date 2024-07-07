package exercise;

import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage {
    private String filepath;
    public FileKV(String pathToFile, Map<String, String> map) {
        filepath = pathToFile;
        Utils.writeFile(pathToFile, Utils.serialize(map));
    }

    @Override
    public void set(String key, String value) {
        Map<String, String> map = Utils.unserialize(Utils.readFile(filepath));
        map.put(key, value);
        Utils.writeFile(filepath, Utils.serialize(map));
    }

    @Override
    public void unset(String key) {
        Map<String, String> map = Utils.unserialize(Utils.readFile(filepath));
        map.remove(key);
        Utils.writeFile(filepath, Utils.serialize(map));
    }

    @Override
    public String get(String key, String defaultValue) {
        return Utils.unserialize(Utils.readFile(filepath)).getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return Utils.unserialize(Utils.readFile(filepath));
    }
}
// END
