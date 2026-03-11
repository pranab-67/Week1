import java.util.*;

class InventoryManager {
    private final Map<String, Integer> stock = new HashMap<>();
    private final Map<String, Queue<String>> waitingList = new HashMap<>();

    public void addProduct(String productId, int count) {
        stock.put(productId, count);
        waitingList.put(productId, new LinkedList<>());
    }

    public synchronized boolean purchaseItem(String productId, String userId) {
        int available = stock.getOrDefault(productId, 0);
        if (available > 0) {
            stock.put(productId, available - 1);
            return true;
        } else {
            waitingList.get(productId).add(userId);
            return false;
        }
    }

    public int checkStock(String productId) {
        return stock.getOrDefault(productId, 0);
    }

    public Queue<String> getWaitingList(String productId) {
        return waitingList.get(productId);
    }
}

public class Question2 {
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();
        manager.addProduct("IPHONE15_256GB", 100);

        System.out.println(manager.purchaseItem("IPHONE15_256GB", "user1"));
        System.out.println(manager.checkStock("IPHONE15_256GB"));
    }
}