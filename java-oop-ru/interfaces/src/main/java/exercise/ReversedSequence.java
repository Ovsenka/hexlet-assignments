package exercise;

// BEGIN
public class ReversedSequence implements CharSequence{
    private CharSequence reversedString;
    public ReversedSequence(String string) {
        String reversedStr = new StringBuilder(string).reverse().toString();
        reversedString = reversedStr.subSequence(0, string.length());
    }

    @Override
    public int length() {
        return reversedString.length();
    }

    @Override
    public char charAt(int index) {
        return reversedString.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return reversedString.subSequence(start, end);
    }

    @Override
    public String toString() {
        return reversedString.toString();
    }
}
// END
