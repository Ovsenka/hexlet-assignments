package exercise;

// BEGIN
public class MaxThread extends Thread {
    private Integer result;
    private int[] numbers;
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    public MaxThread(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        LOGGER.info("Thread " + Thread.currentThread().getName() + " started");
        result = Arrays.stream(numbers).max().getAsInt();
        LOGGER.info("Thread " + Thread.currentThread().getName() + " finished");
    }

    public Integer getResult() {
        return result;
    }
}
// END
