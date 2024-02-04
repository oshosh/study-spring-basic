package hello.core.singleton;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {
  @Test
  void statefulServiceSingleton() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
    StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
    StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

    statefulService1.order("userA", 10000);
    statefulService1.order("userB", 20000);

    int price = statefulService1.getPrice();
    System.out.println("price = " + price); // 20000 -> 같은 객체에 값을 넣음

    Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
  }

  static class TestConfig {
    @Bean
    public StatefulService statefulService() {
      // 무상태 설계가 필수적임..
      return new StatefulService();
    }
  }
}