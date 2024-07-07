package exercise;

// BEGIN
public class LabelTag implements TagInterface {
    private String tagValue;
    private TagInterface tag;

    public LabelTag(String tagValue, TagInterface tag) {
        this.tagValue = tagValue;
        this.tag = tag;
    }

    @Override
    public String render() {
        return "<label>%s%s</label>".formatted(tagValue, tag.render());
    }
}
// END
