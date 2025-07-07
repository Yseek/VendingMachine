package step03_ocp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class VendingMachineTest {

    @Test
    @DisplayName("주어진 상품 목록으로 정상 동작한다")
    void successfulPurchase() {
        System.out.println("--- OCP 성공 시나리오 테스트 ---");

        //given
        final List<Item> items = Arrays.asList(new Coke(), new Cider());
        final VendingMachine machine = new VendingMachine(items);

        //when
        machine.insertMoney(5000);
        machine.selectItem("Coke");

        //then
        machine.returnChange();
        machine.displayStock();
    }

    @Test
    @DisplayName("주입되지 않은 상품은 선택할 수 없다")
    void selectUnavailableItem() {
        System.out.println("--- OCP 없는 상품 선택 테스트 ---");
        //given
        final List<Item> items = Collections.singletonList(new Coke());
        final VendingMachine machine = new VendingMachine(items);

        //when
        machine.insertMoney(5000);
        machine.selectItem("Cider");

        //then
    }

    @Test
    @DisplayName("새로운 상품을 추가해도 잘 동작한다")
    void extendWithNewItem() {
        System.out.println("--- OCP 확장성 테스트 ---");
        //given
        class Fanta extends Item {
            public Fanta() {
                super("Fanta", 1300);
            }
        }

        // Fanta 를 포함한 상품 목록 구성
        final List<Item> items = Arrays.asList(new Coke(), new Cider(), new Fanta());
        final VendingMachine machine = new VendingMachine(items);

        //when
        machine.displayStock();
        machine.insertMoney(5000);
        machine.selectItem("Fanta");

        //then
        machine.displayStock();
    }
}