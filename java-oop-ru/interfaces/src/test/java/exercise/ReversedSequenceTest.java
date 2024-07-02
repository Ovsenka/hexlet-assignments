package exercise;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ReversedSequenceTest {
    @Test
    void testReverseText() {
        ReversedSequence reversed = new ReversedSequence("hello");
        assertThat(reversed.toString()).isEqualTo("olleh");
        assertThat(reversed.charAt(0)).isEqualTo('o');
        assertThat(reversed.length()).isEqualTo(5);
        assertThat(reversed.subSequence(0, 2).toString()).isEqualTo("ol");

    }
}
