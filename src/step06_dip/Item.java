package step06_dip;

/**
 * 상품 정보
 */
public abstract class Item {
    private String name;
    private int price;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    // 모든 자식 클래스가 구현해야 할 '준비' 행동
    // 일반 음료는 아무것도 안 하면 되고, 뜨거운 음료는 데우면 된다.
    public abstract void prepare();
}
