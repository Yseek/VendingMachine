package step04_lsp;

public class HotAmericano extends Item {
    public HotAmericano() {
        super("HotAmericano", 1500);
    }

    @Override
    public void prepare() {
        // 뜨거운 아메리카노의 준비 과정은 '데우기'이다.
        System.out.println(STR."\{getName()}를 데우는 중... 완료!");
    }

//    public void warmUp() {
//        System.out.println(STR."\{getName()}를 데우는 중... 완료!");
//    }

}
