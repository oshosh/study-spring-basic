package hello.core.scope;

import static org.assertj.core.api.Assertions.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonTest {
  @Test
  void SingletonBeanFind() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        SingletonBean.class);

    SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
    SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);

    System.out.println("singletonBean1 = " + singletonBean1);
    System.out.println("singletonBean2 = " + singletonBean2);
    assertThat(singletonBean1).isSameAs(singletonBean2);

    ac.close();
  }

  @Scope("singleton")
  static class SingletonBean {
    @PostConstruct
    public void init() {
      System.out.println("SingletonBean.init");
    }
    
    @PreDestroy
    public void destroy() {
      System.out.println("SingletonBean.destroy");
    }
  }
}


/*
*
* SingletonBean.init
singletonBean1 = hello.core.scope.SingletonTest$SingletonBean@5cc69cfe
singletonBean2 = hello.core.scope.SingletonTest$SingletonBean@5cc69cfe
22:49:41.781 [Test worker] DEBUG org.springframework.context.annotation.AnnotationConfigApplicationContext - Closing org.springframework.context.annotation.AnnotationConfigApplicationContext@4535b6d5, started on Sun Feb 11 22:49:41 KST 2024
SingletonBean.destroy
*
* */