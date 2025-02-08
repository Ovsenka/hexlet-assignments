package exercise;

import java.util.logging.Logger;

// BEGIN
public class MaxThread extends Thread {

    int[] arr;
    int max;

    public MaxThread(int[] arr) {
        this.arr = arr;
    }

    @Override
    public void run() {
        max = Arrays.stream(arr).max().getAsInt();
    }
}
// END
