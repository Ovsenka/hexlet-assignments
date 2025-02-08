package exercise;

import java.util.Map;
import java.util.logging.Logger;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static void main(String[] args) {
        int[] numbers = {10, -4, 67, 100, -100, 8};
        Map<String, Integer> result = getMinMax(numbers);
        System.out.println(result);
    }

    public static Map<String, Integer> getMinMax(int[] numbers) {
        Map<String, Integer> result = new HashMap<>();

        MinThread minThread = new MinThread(numbers);
        LOGGER.info("Thread " + minThread.getName() + " started");
        MaxThread maxThread = new MaxThread(numbers);
        LOGGER.info("Thread " + maxThread.getName() + " started");

        maxThread.start();
        LOGGER.info("Thread " + maxThread.getName() + " started");
        minThread.start();
        LOGGER.info("Thread " + minThread.getName() + " started");

        try {
            minThread.join();
            maxThread.join();
            LOGGER.info("Thread " + minThread.getName() + " finished");
            LOGGER.info("Thread " + minThread.getName() + " finished");
        } catch (InterruptedException e) {
            LOGGER.info("Thread interrupted: " + e.getMessage());
        }

        result.put("min", minThread.getResult());
        result.put("max", maxThread.getResult());

        return result;
    }
    // END
}
