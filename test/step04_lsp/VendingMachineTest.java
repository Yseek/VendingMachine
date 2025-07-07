package step04_lsp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class VendingMachineTest {

    @Test
    @DisplayName("일반 상품을 선택하면, 일반 준비 과정 실행")
    void testWithNormalItem() {
        System.out.println("--- LSP 일반 상품 테스트 ---");
        //given
        final List<Item> items = Arrays.asList(new Coke());
        final VendingMachine machine = new VendingMachine(items);

        //when
        machine.insertMoney(5000);
        machine.selectItem("Coke");

        //then
        machine.displayStock();
    }

    @Test
    @DisplayName("특별한 상품(HotAmericano)을 선택하면, 특별 준비 과정 실행")
    void testWithSpecialItem() {
        System.out.println("--- LSP 특별 상품 테스트 ---");
        //given
        final List<Item> items = Arrays.asList(new HotAmericano());
        final VendingMachine machine = new VendingMachine(items);

        //when
        machine.insertMoney(5000);
        machine.selectItem("HotAmericano");

        //then
        machine.displayStock();
    }

    @Test
    @DisplayName("여러 종류의 상품을 선택해도 각자 맞는 준비 과정 실행")
    void testWithMixedItem() {
        System.out.println("--- LSP 혼합 상품 테스트 ---");
        //given
        final List<Item> items = Arrays.asList(new Coke(), new HotAmericano(), new Cider());
        final VendingMachine machine = new VendingMachine(items);

        //when
        machine.insertMoney(5000);

        System.out.println("* 콜라 선택");
        machine.selectItem("Coke");

        System.out.println("\n* 아메리카노 선택");
        machine.selectItem("HotAmericano");

        //then
        machine.displayStock();
    }
}