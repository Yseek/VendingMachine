package step02_srp;

/**
 * 결제 관리
 */
public class PaymentProcessor {
    private int currentMoney = 0;

    public void insertMoney(int money) {
        this.currentMoney += money;
        System.out.println(STR."투입 금액: \{money}원 / 현재 잔액: \{currentMoney}원");
    }

    public boolean isPaymentSufficient(int price) {
        return currentMoney >= price;
    }

    public void processPayment(int price) {
        int change = currentMoney - price;
        this.currentMoney = change;
        System.out.println(STR."거스름돈: \{change}원");
    }

    public int getCurrentMoney() {
        return currentMoney;
    }
}
