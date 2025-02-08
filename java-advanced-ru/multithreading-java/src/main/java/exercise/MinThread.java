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
        LOGGER.info("Thread " + Thread.currentThread().getName() + " started");
        min = Arrays.stream(arr).min().getAsInt();
        LOGGER.info("Thread " + Thread.currentThread().getName() + " finished");
    }
}
// END
