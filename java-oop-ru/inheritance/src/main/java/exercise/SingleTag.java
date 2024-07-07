package exercise;

import java.util.Map;

// BEGIN
public class SingleTag extends Tag {

    public SingleTag(String tagName, Map<String, String> attrs) {
        super.setTagName(tagName);
        super.setAttributes(attrs);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<" + getTagName());
        for (Map.Entry entry : getAttributes().entrySet()) {
            sb.append(" %s=\"%s\"".formatted(entry.getKey(), entry.getValue()));
        }
        sb.append(">");
        return sb.toString();
    }
}
// END
