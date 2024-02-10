package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
  @Test
  public void lifeCycleTest() {
   ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(
        LifeCycleConfig.class);
    NetworkClient client = ac.getBean(NetworkClient.class);
    ac.close();
  }

  @Configuration
  static class LifeCycleConfig {
//    @Bean(initMethod = "init", destroyMethod = "close")
    @Bean
    public NetworkClient networkClient() {
      NetworkClient networkClient = new NetworkClient();
      networkClient.setUrl("http://hello-spring.dev");
      return networkClient;
    }
  }
}

/*
 빈 생명 주기를 제대로 활용하는 방법 3가지
 1. @Bean(initMethod = "init", destroyMethod = "close") => Bean에 init과 destroy 메서드 함수 생성하여 정해주기
 2. implements InitializingBean, DisposableBean 인터페이스 오버라이드 생성 후 메서드에서 처리
 3. @PostConstruct, @PreDestroy 사용 (예외 대신 외부라이브러리 지원 안하기에 2번 사용해야함)
 */