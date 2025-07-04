package step02_srp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PaymentProcessorTest {

    private PaymentProcessor paymentProcessor;

    @BeforeEach
    void setUp() {
        paymentProcessor = new PaymentProcessor();
    }

    @Test
    @DisplayName("돈을 넣으면 현재 금액이 증가한다")
    void insertMoney() {
        //given
        int money = 1000;
        //when
        paymentProcessor.insertMoney(money);
        //then
        assertThat(paymentProcessor.getCurrentMoney()).isEqualTo(money);
    }

    @Test
    @DisplayName("현재 금액이 가격보다 많거나 같으면 지불 가능")
    void isPaymentSufficient() {
        //given
        int money = 2000;
        //when
        paymentProcessor.insertMoney(money);
        //then
        assertThat(paymentProcessor.isPaymentSufficient(2000)).isEqualTo(true);
    }

    @Test
    @DisplayName("현재 금액이 가격보다 적으면 지불 불가")
    void isPaymentNotSufficient() {
        //given
        int money = 1000;
        //when
        paymentProcessor.insertMoney(money);
        //then
        assertThat(paymentProcessor.isPaymentSufficient(2000)).isEqualTo(false);
    }
}