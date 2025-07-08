package step06_dip;

// 재고 관리 인터페이스
public interface Storable {
    void addItem(Item item, int quantity);
    void addStock(Item item, int quantity);
    boolean isInStock(Item item);
    void releaseItem(Item item);
    void displayStock();
}
