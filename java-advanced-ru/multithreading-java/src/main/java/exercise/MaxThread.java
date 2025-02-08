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
        LOGGER.info("Thread " + Thread.currentThread().getName() + " started");
        max = Arrays.stream(arr).max().getAsInt();
        LOGGER.info("Thread " + Thread.currentThread().getName() + " finished");
    }
}
// END
