import java.util.concurrent.*;

public class Oven implements Runnable {
    private final BlockingQueue<Order> bakingQueue = new LinkedBlockingQueue<>();
    private final BlockingQueue<Order> finishedQueue = new LinkedBlockingQueue<>();
    private boolean running = true;

    public void addOrder(Order order) {
        bakingQueue.add(order);
    }

    public Order takeFinishedOrder() throws InterruptedException {
        return finishedQueue.take();
    }

    @Override
    public void run() {
        while (running || !bakingQueue.isEmpty()) {
            try {
                Order order = bakingQueue.take();
                bake(order);
                finishedQueue.add(order);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private void bake(Order order) throws InterruptedException {
        Thread.sleep(2500);
    }

    public void stop() {
        running = false;
    }
}
