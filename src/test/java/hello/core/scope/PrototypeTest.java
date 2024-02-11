package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {

  @Test
  public void prototypeBeanFind() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

    PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
    PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

    System.out.println("prototypeBean1 = " + prototypeBean1);
    System.out.println("prototypeBean2 = " + prototypeBean2);

    assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

    ac.close();
  }


  @Scope("prototype")
  static class PrototypeBean{
    @PostConstruct
    public void init() {
      System.out.println("PrototypeBean.init");
    }

    @PreDestroy
    public void destroy() {
      System.out.println("PrototypeBean.destroy");
    }
  }
}

/*
*
* PrototypeBean.init
PrototypeBean.init
prototypeBean1 = hello.core.scope.PrototypeTest$PrototypeBean@29cfd92b
prototypeBean2 = hello.core.scope.PrototypeTest$PrototypeBean@21c64522
22:50:09.687 [Test worker] DEBUG org.springframework.context.annotation.AnnotationConfigApplicationContext - Closing org.springframework.context.annotation.AnnotationConfigApplicationContext@1ecee32c, started on Sun Feb 11 22:50:09 KST 2024
*
* */