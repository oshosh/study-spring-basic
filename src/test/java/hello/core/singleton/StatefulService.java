package hello.core.singleton;

// 공유 필드 사용시 스프링 빈은 무상태로 설계해야함.
public class StatefulService {

  private int price; // price를 공유하게 쓰면 같은 객체에서 사용이 지속됨...

  public void order(String name, int price) {
    System.out.println("name = " + name + " price = " + price);
    this.price = price;
  }

  public int getPrice() {
    return price;
  }
}

// 지역 변수로 대체
//package hello.core.singleton;
//
//public class StatefulService {
//
//  public void order(String name, int price) {
//    System.out.println("name = " + name + " price = " + price);
//    return price
//  }
//
//  public int getPrice() {
//    return price;
//  }
//}
