package exercise;

import java.util.logging.Logger;

// BEGIN
public class MinThread extends Thread {

    int[] arr;
    int min;

    public MinThread(int[] arr) {
        this.arr = arr;
    }

    @Override
    public void run() {
        min = Arrays.stream(arr).min().getAsInt();
    }
}
// END
