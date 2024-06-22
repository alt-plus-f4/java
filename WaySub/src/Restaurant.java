import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
    private final List<Cook> cooks = new ArrayList<>();
    private final BlockingQueue<Order> waitingOrders = new LinkedBlockingQueue<>();
    private final Map<Integer, Order> completedOrders = new ConcurrentHashMap<>();
    private final ExecutorService cookExecutor = Executors.newCachedThreadPool();
    private static Restaurant instance;

    Restaurant() {}

    public static synchronized Restaurant getInstance() {
        if (instance == null) {
            instance = new Restaurant();
        }
        return instance;
    }

    public synchronized void addCook(Cook cook) throws IllegalArgumentException {
        for (Cook c : cooks) {
            if (c.getName().equals(cook.getName())) {
                throw new IllegalArgumentException("Cook with the same name already exists");
            }
        }
        cooks.add(cook);
        cookExecutor.submit(cook);
    }

    public void addOrder(Order order) {
        waitingOrders.add(order);
        assignOrdersToCooks();
    }

    public void finalizeOrder(Order order) {
        completedOrders.put(order.getId(), order);
    }

    public void shutdown() throws InterruptedException {
        cookExecutor.shutdown();
    }

    private synchronized void assignOrdersToCooks() {
        for (Cook cook : cooks) {
            if (waitingOrders.isEmpty()) break;
            if (cook.isAvailable()) {
                cook.addOrder(waitingOrders.poll());
            }
        }
    }

    public Order getOrderById(int id) {
        return completedOrders.get(id);
    }
}
