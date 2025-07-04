import java.util.HashMap;
import java.util.Map;

public class VendingMachine {

    private Map<String, Integer> items; // key: 음료수 이름, value: 가격
    private Map<String, Integer> stock; // key: 음료수 이름, value: 재고
    private int currentMoney = 0; // 현재 투입된 금액

    public VendingMachine() {
        this.items = new HashMap<>();
        this.stock = new HashMap<>();

        // 초기 상품 세팅
        items.put("Coke", 1200);
        stock.put("Coke", 5);
        items.put("Cider", 1100);
        stock.put("Cider", 5);
        items.put("Water", 800);
        stock.put("Water", 10);
    }

    // 1. 돈 투입 기능
    public void insertMoney(int money) {
        this.currentMoney += money;
        final String result = STR."투입 금액: \{money}원 / 현재 잔액: \{currentMoney}원";
        System.out.println("result = " + result);
    }

    // 2. 음료 선택 및 판매 기능 (가장 많은 책임을 가진 메소드)
    public void selectItem(String itemName) {
        // 유효성 검사 1: 존재하는 상품인가?
        if (!items.containsKey(itemName)) {
            System.out.println(STR."오류: '\{itemName}'는 판매하지 않는 상품입니다.");
            return;
        }

        final int price = items.get(itemName);
        final int currentStock = stock.get(itemName);

        // 유효성 검사 2: 재고가 있는가?
        if (currentStock <= 0) {
            System.out.println(STR."오류: '\{itemName}'는 품절되었습니다.");
            return;
        }

        // 유효성 검사 3: 돈이 충분한가?
        if (currentMoney < price) {
            System.out.println(STR."오류: 잔액이 부족합니다. (부족한 금액: \{price - currentMoney}원");
            return;
        }

        // 처리
        // 1) 재고 차감
        stock.put(itemName, currentStock - 1);

        // 2) 금액 계산 및 거스름돈 반환
        final int change = currentMoney - price;
        this.currentMoney = 0;

        // 3) 결과 출력
        System.out.println(STR."'\{itemName}'가 나왔습니다.");
        System.out.println(STR."거스름돈: \{change}원");
    }

    public void displayStock() {
        System.out.println("--- 현재 재고 현황 ---");
        for (String itemName : stock.keySet()) {
            System.out.println(STR."\{itemName}: \{stock.get(itemName)}개");
        }
        System.out.println("--------------------");
    }
}
