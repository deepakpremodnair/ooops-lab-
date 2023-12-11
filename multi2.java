import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class RailwayTrack {
    private Lock lock = new ReentrantLock();

    public void useTrack(String trainName) {
        lock.lock();
        try {
            System.out.println(trainName + " is using the railway track.");
            Thread.sleep(2000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(trainName + " has finished using the railway track.");
            lock.unlock();
        }
    }
}

class Train extends Thread {
    private String trainName;
    private RailwayTrack track;

    public Train(String trainName, RailwayTrack track) {
        this.trainName = trainName;
        this.track = track;
    }

    public void run() {
        track.useTrack(trainName);
    }
}

public class multi2 {
    public static void main(String[] args) {
        RailwayTrack track = new RailwayTrack();

        Train train1 = new Train("Train 1", track);
        Train train2 = new Train("Train 2", track);

        train1.start();
        train2.start();
    }
}
