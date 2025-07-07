package step04_lsp;

public class Cider extends Item {
    public Cider() {
        super("Cider", 1100);
    }

    @Override
    public void prepare() {
        // 사이다는 특별한 준비 과정이 필요 없음
        System.out.println(STR."\{getName()}는 바로 제공됩니다.");
    }
}
