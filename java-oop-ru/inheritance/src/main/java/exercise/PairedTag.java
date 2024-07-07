package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {
    private String tagBody;
    private List<Tag> internalTags;
    PairedTag(String tagName, Map<String, String> attrs, String tagBody, List<Tag> internalTags) {
        super.setTagName(tagName);
        super.setAttributes(attrs);
        this.tagBody = tagBody;
        this.internalTags = internalTags;
    }

    public String getTagBody() {
        return tagBody;
    }

    public void setTagBody(String tagBody) {
        this.tagBody = tagBody;
    }

    public List<Tag> getInternalTags() {
        return internalTags;
    }

    public void setInternalTags(List<Tag> internalTags) {
        this.internalTags = internalTags;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<" + getTagName());
        for (Map.Entry entry : getAttributes().entrySet()) {
            sb.append(" %s=\"%s\"".formatted(entry.getKey(), entry.getValue()));
        }
        sb.append(">");
        for (Tag tag : internalTags) {
            sb.append(tag.toString());
        }
        sb.append(tagBody);
        sb.append("</" + getTagName() + ">");
        return sb.toString();
    }
}
// END
