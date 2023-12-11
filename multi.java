import java.util.Random;

class RandomNumberGenerator implements Runnable {
    private Random random = new Random();

    @Override
    public void run() {
        try {
            while (true) {
                int number = random.nextInt(100);
                System.out.println("Generated number: " + number);

                if (number % 2 == 0) {
                    Thread evenThread = new Thread(new SquareCalculator(number));
                    evenThread.start();
                    evenThread.join();
                } else {
                    Thread oddThread = new Thread(new CubeCalculator(number));
                    oddThread.start();
                    oddThread.join();
                }

                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SquareCalculator implements Runnable {
    private int number;

    public SquareCalculator(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        int square = number * number;
        System.out.println("Square of " + number + ": " + square);
    }
}

class CubeCalculator implements Runnable {
    private int number;

    public CubeCalculator(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        int cube = number * number * number;
        System.out.println("Cube of " + number + ": " + cube);
    }
}

public class multi{
    public static void main(String[] args) {
        Thread generatorThread = new Thread(new RandomNumberGenerator());
        generatorThread.start();
    }
}
