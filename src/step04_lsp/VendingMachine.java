package step04_lsp;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 밴딩 머신
 */
public class VendingMachine {
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

    public void insertMoney(int money) {
        paymentProcessor.insertMoney(money);
    }

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

    public void returnChange() {
        paymentProcessor.returnChange();
    }

    // 관리자 기능은 Inventory 객체에 위임
    public void displayStock() {
        inventory.displayStock();
    }
}
