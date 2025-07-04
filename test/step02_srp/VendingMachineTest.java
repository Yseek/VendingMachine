package step02_srp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VendingMachineTest {

    private VendingMachine machine;

    @BeforeEach
    void setUp() {
        machine = new VendingMachine();
    }

    @Test
    @DisplayName("성공 시나리오: 돈을 넣고 상품을 성공적으로 구매한다")
    void successfulPurchase() {
        System.out.println("--- 성공 시나리오 테스트 ---");
        //given
        machine.insertMoney(5000);
        //when
        machine.selectItem("Coke");
        //then
        machine.displayStock(); // 재고가 줄었는지 확인
    }

    @Test
    @DisplayName("실패 시나리오: 재고가 부족하면 품절 메시지를 출력한다")
    void itemOutOfStock() {
        System.out.println("--- 재고 부족 테스트 ---");
        //given
        VendingMachine machineWithNoStock = new VendingMachine();
        machineWithNoStock.insertMoney(10000);

        // When
        machineWithNoStock.selectItem("Coke");
        machineWithNoStock.selectItem("Coke");
        machineWithNoStock.selectItem("Coke");
        machineWithNoStock.selectItem("Coke");
        machineWithNoStock.selectItem("Coke"); // 재고 소진
        machineWithNoStock.selectItem("Coke");

        // Then: 콘솔에서 "품절되었습니다" 메시지를 확인
    }

    @Test
    @DisplayName("실패 시나리오: 잔액이 부족하면 부족 메시지를 출력한다")
    void insufficientFunds() {
        System.out.println("\n--- 잔액 부족 테스트 ---");
        // Given
        machine.insertMoney(1000); // 콜라 가격(1200)보다 부족하게 넣음

        // When
        machine.selectItem("Coke");

        // Then: 콘솔에서 "잔액이 부족합니다" 메시지를 확인
    }
}