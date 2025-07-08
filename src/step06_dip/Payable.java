package step06_dip;

// 결제 처리 인터페이스
public interface Payable {
    void insertMoney(int money);
    boolean isPaymentSufficient(int price);
    void processPayment(int price);
    void returnChange();
    int getCurrentMoney();
}
