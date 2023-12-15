package hello.core.singleton;

public class StatefulService {

//    private int price; // 상태를 유지하는 필드. 무상태로 설계하려면 공유필드가 존재하지 않도록 해야 함

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
//        this.price = price; // 여기서 문제 발생
        return price;
    }

//    public int getPrice() {
//        return price;
//    }

}
