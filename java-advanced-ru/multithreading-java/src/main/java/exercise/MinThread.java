package exercise;

// BEGIN
public class MinThread extends Thread{
    private Integer result;
    private int[] numbers;
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    public MinThread(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {

        result = Arrays.stream(numbers).min().getAsInt();
        LOGGER.info("Thread " + Thread.currentThread().getName() + " finished");
    }

    public Integer getResult() {
        return result;
    }
}
// END
