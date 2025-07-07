package step04_lsp;

public class Coke extends Item {
    public Coke() {
        super("Coke", 1200);
    }

    @Override
    public void prepare() {
        // 콜라는 특별한 준비 과정이 필요 없음
        System.out.println(STR."\{getName()}는 바로 제공됩니다.");
    }
}
