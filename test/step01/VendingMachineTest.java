package step01;

import org.junit.jupiter.api.Test;

class VendingMachineTest {

    @Test
    void test() {
        // 1. 안티-SOLID 자판기 객체
        final VendingMachine machine = new VendingMachine();

        // 2. 시나리오를 실행하며 테스트합니다.
        System.out.println("=== 자판기 작동 시작 ===");
        machine.displayStock(); // 초기 재고 확인
        machine.insertMoney(5000); // 5000원 투입
        machine.selectItem("Coke"); // 콜라 선택
        System.out.println("=== 콜라 구매 후 ===");
        machine.displayStock(); // 변경된 재고 확인
        machine.insertMoney(1000); // 다시 1000원 투입
        machine.selectItem("Water"); // 물 선택
        machine.displayStock();
    }
}