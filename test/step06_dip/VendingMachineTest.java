package step06_dip;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class VendingMachineTest {

    @Test
    @DisplayName("표준 부품(Inventory, PaymentProcessor)을 주입했을 때 정상 동작한다")
    void testWithStandardParts() {
        System.out.println("--- DIP 표준 부품 테스트 ---");
        //given
        final Storable inventory = new Inventory();
        final Payable paymentProcessor = new PaymentProcessor();
        final List<Item> items = Arrays.asList(new Coke(), new HotAmericano(), new Water());

        //when
        final VendingMachine machine = new VendingMachine(items, inventory, paymentProcessor);

        //then
        final UserOperations user = machine;
        user.insertMoney(5000);
        user.selectItem("Coke");
        user.selectItem("HotAmericano");
        user.returnChange();

        final AdminOperations admin = machine;
        admin.displayStock();
        admin.addStock("HotAmericano", 5);
        admin.displayStock();
    }

    @Test
    @DisplayName("교체용 부품(테스트용 가짜 객체)을 주입해도 동작한다")
    void testWithFakeParts() {
        System.out.println("--- DIP 교체용 부품 테스트 ---");

        //given
        class FakePaymentProcessor implements Payable {
            @Override
            public void insertMoney(int money) {
                System.out.println(STR."가짜 모듈: \{money} 입금");
            }

            @Override
            public boolean isPaymentSufficient(int price) {
                return true;
            }

            @Override
            public void processPayment(int price) {
                System.out.println("가짜 모듈: 결제 성공 처리");
            }

            @Override
            public void returnChange() {
                System.out.println("가짜 모듈: 거스름돈");
            }

            @Override
            public int getCurrentMoney() {
                return 0;
            }
        }

        class FakeInventory implements Storable {
            @Override
            public void addItem(Item item, int quantity) {}
            @Override
            public void addStock(Item item, int quantity) {}
            @Override
            public boolean isInStock(Item item) {return true;}
            @Override
            public void displayStock() {
                System.out.println("가짜 모듈: 재고 확인");
            }
            @Override
            public void releaseItem(Item item) {
                System.out.println(STR."가짜 모듈: \{item.getName()} 재고 차감");
            }
        }

        final Storable fakeInventory = new FakeInventory();
        final Payable fakePayment = new FakePaymentProcessor();
        final List<Item> items = Arrays.asList(new Coke(), new HotAmericano(), new Water());

        //when
        final VendingMachine machine = new VendingMachine(items, fakeInventory, fakePayment);

        //then
        final UserOperations user = machine;
        user.insertMoney(5000);
        user.selectItem("Coke");
        user.selectItem("HotAmericano");
        user.returnChange();

        final AdminOperations admin = machine;
        admin.displayStock();
    }

}