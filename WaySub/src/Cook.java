import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook implements Runnable {
    private final String name;
    private final BlockingQueue<Order> ordersQueue = new LinkedBlockingQueue<>();
    private boolean available = true;

    public Cook(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void addOrder(Order order) {
        ordersQueue.add(order);
        available = false;
    }

    public void log(String msg) {
        System.out.println("[Cook " + name + "] " + msg);
    }

    @Override
    public void run() {
        try {
            while (true) {
                Order order = ordersQueue.take();
                log("Starting order " + order.getId());
                for (Object ingredient : order.getIngredients()) {
                    Thread.sleep(ingredient instanceof Bread ? 1500 : 1200);
                    log("Added " + ingredient);
                }
                if (!order.getSpecialRequests().contains(SpecialRequest.NO_BAKE)) {
                    log("Baking sandwich");
                    Thread.sleep(2500);
                }
                log("Order " + order.getId() + " is ready");
                available = true;
                Restaurant.getInstance().finalizeOrder(order);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log("Interrupted");
        }
    }
}
