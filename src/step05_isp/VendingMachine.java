package step05_isp;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * VendingMachine은 고객과 관리자의 모든 기능을 구현하지만,
 * 두 개의 역할 인터페이스를 따르도록 한다.
 */

public class VendingMachine implements UserOperations, AdminOperations {
    private final Inventory inventory;
    private final PaymentProcessor paymentProcessor;
    private final Map<String, Item> items; // Drink 타입으로 변경

    // 생성자를 통해 판매할 음료 목록을 외부에서 받는다. (의존성 주입)
    public VendingMachine(List<Item> drinks) {
        this.inventory = new Inventory();
        this.paymentProcessor = new PaymentProcessor();

        // 외부에서 받은 Drink 리스트로 아이템 맵을 구성
        this.items = drinks.stream()
                .collect(Collectors.toMap(Item::getName, Function.identity()));

        // 재고 초기화
        this.items.values().forEach(item -> inventory.addItem(item, 5));
    }

    @Override
    public void insertMoney(int money) {
        paymentProcessor.insertMoney(money);
    }

    @Override
    public void selectItem(String itemName) {
        final Item item = items.get(itemName);
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

        // LSP 위반 지점
        // 자식의 타입을 직접 확인하고(instanceof),
        // 자식 타입으로 강제 형변환(downcasting)을 하고 있다.
//        if (item instanceof HotAmericano hotItem) {
//            hotItem.warmUp(); // 자식의 특별한 메소드를 호출
//        }

        // LSP 준수
        // 자판기는 더 이상 자식의 타입을 궁금해 하지 않는다.
        // 부모 타임(Item)이 정의한 규약(prepare)대로 명령하면 된다.
        item.prepare();

        inventory.releaseItem(item);
        paymentProcessor.processPayment(item.getPrice());
        System.out.println(STR."\{itemName}가 나왔습니다.");
    }

    @Override
    public void returnChange() {
        paymentProcessor.returnChange();
    }

    @Override
    public void addStock(String itemName, int quantity) {
        final Item item = items.get(itemName);
        if (item != null) {
            inventory.addStock(item, quantity);
            System.out.println(STR."[관리] \{itemName}의 재고가 \{quantity}개 추가되었습니다.");
        }
    }

    @Override
    public void displayStock() {
        inventory.displayStock();
    }
}
