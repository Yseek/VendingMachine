package step05_isp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class VendingMachineTest {

    private VendingMachine machine;

    @BeforeEach
    void setUp() {
        // 모든 테스트에서 사용할 공통 자판기 객체
        machine = new VendingMachine(
                Arrays.asList(new Coke(), new HotAmericano(), new Cider(), new Water())
        );
    }

    @Test
    @DisplayName("고객 관점: 고객은 고객 기능에만 접근 가능")
    void testFromUserPerspective() {
        System.out.println("--- ISP 고객 관점 테스트 ---");
        //given
        final UserOperations userClient = machine;

        //when
        userClient.insertMoney(5000);
        userClient.selectItem("Coke");
        userClient.selectItem("HotAmericano");
        userClient.selectItem("Cider");
        userClient.returnChange();

        //then
        // userClient.addStock("Coke", 5); // 이 코드는 컴파일 에러가 발생한다.
        // 왜냐하면 UserOperations 인터페이스에는 addStock() 메소드가 없기 때문
        System.out.println("고객 기능이 정상 실행되었습니다.");
    }

    @Test
    @DisplayName("관리자 관점: 관리자는 관리자 기능에만 접근 가능")
    void testFromAdminPerspective() {
        System.out.println("--- ISP 관리자 관점 테스트 ---");

        //given
        AdminOperations adminClient = machine;

        //when
        adminClient.displayStock();
        adminClient.addStock("Coke", 11);
        adminClient.addStock("HotAmericano", 12);

        //then
        adminClient.displayStock();
    }
}