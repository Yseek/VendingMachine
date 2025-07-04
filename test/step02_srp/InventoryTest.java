package step02_srp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InventoryTest {

    private Inventory inventory;
    private Item coke = new Item("coke", 1200);

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
    }

    @Test
    @DisplayName("상품을 추가하면 재고가 있는지 확인된다")
    void addItemAndCheckStock() {
        //given

        //when
        inventory.addItem(coke, 5);
        //then
        assertThat(inventory.isInStock(coke)).isEqualTo(true);
    }

    @Test
    @DisplayName("재고가 없는 상품은 없다고 확인된다")
    void checkOutOfStock() {
        //given
        Item cider = new Item("cider", 1000);
        //when
        inventory.addItem(cider, 5);
        //then
        assertThat(inventory.isInStock(coke)).isEqualTo(false);
    }

    @Test
    @DisplayName("상품을 내주면 재고가 1 감소한다")
    void releaseItem() {
        //given
        inventory.addItem(coke, 1);
        //when
        inventory.releaseItem(coke);
        //then
        assertThat(inventory.isInStock(coke)).isEqualTo(false);
    }
}