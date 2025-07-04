package step02_srp;

import java.util.HashMap;
import java.util.Map;

/**
 * 밴딩 머신
 */
public class VendingMachine {
    private final Inventory inventory;
    private final PaymentProcessor paymentProcessor;
    private final Map<String, Item> items;

    public VendingMachine() {
        this.inventory = new Inventory();
        this.paymentProcessor = new PaymentProcessor();
        this.items = new HashMap<>();
        initializeItems();
    }

    private void initializeItems() {
        Item coke = new Item("Coke", 1200);
        Item cider = new Item("Cider", 1100);
        Item water = new Item("Water", 800);

        items.put("Coke", coke);
        items.put("Cider", cider);
        items.put("Water", water);

        inventory.addItem(coke, 5);
        inventory.addItem(cider, 5);
        inventory.addItem(water, 10);
    }

    public void insertMoney(int money) {
        paymentProcessor.insertMoney(money);
    }

    public void selectItem(String itemName) {
        Item item = items.get(itemName);
        if (item == null) {
            System.out.println(STR."오류: \{itemName}는 판매하지 않는 상품입니다.");
            return;
        }

        if (!inventory.isInStock(item)) {
            System.out.println(STR."오류: \{itemName}는 품절되었습니다.");
            return;
        }

        if (!paymentProcessor.isPaymentSufficient(item.getPrice())) {
            final int required = item.getPrice() - paymentProcessor.getCurrentMoney();
            System.out.println(STR."오류: 잔액이 부족합니다. (부족한 금액: \{required}원)");
            return;
        }

        inventory.releaseItem(item);
        paymentProcessor.processPayment(item.getPrice());
        System.out.println(STR."\{itemName}가 나왔습니다.");
    }

    // 관리자 기능은 Inventory 객체에 위임
    public void displayStock() {
        inventory.displayStock();
    }
}
