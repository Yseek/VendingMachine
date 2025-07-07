package step05_isp;

public class Water extends Item {
    public Water() {
        super("Water", 800);
    }

    @Override
    public void prepare() {
        // 물은 특별한 준비 과정이 필요 없음
        System.out.println(STR."\{getName()}는 바로 제공됩니다.");
    }
}
