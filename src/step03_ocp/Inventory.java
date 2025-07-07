package step03_ocp;

import java.util.HashMap;
import java.util.Map;

/**
 * 재고 관리
 */
public class Inventory {
    private Map<Item, Integer> stock;

    public Inventory() {
        this.stock = new HashMap<>();
    }

    public void addItem(Item item, int quantity) {
        this.stock.put(item, quantity);
    }

    public boolean isInStock(Item item) {
        return stock.getOrDefault(item, 0) > 0;
    }

    public void releaseItem(Item item) {
        if (isInStock(item)) {
            stock.put(item, stock.get(item) - 1);
        }
    }

    // 재고 확인 등 관리자 기능
    public void displayStock() {
        System.out.println("--- 현재 재고 현황 ---");
        for (Map.Entry<Item, Integer> entry : stock.entrySet()) {
            System.out.println(STR."\{entry.getKey().getName()}: \{entry.getValue()}개");
        }
        System.out.println("--------------------");
    }
}
