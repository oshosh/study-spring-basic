package hello.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonWithPrototypeTest1 {

  // 1. 프로토타입 빈 사용할 때마다 스프링 컨테이너에 요청
  @Test
  void providerTest() {
    // ClientBean, PrototypeBean 빈 등록
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientPrototypeBean.class, PrototypeBean.class);

    // 클라이언트 요청1
    ClientPrototypeBean clientBean1 = ac.getBean(ClientPrototypeBean.class);
    int count1 = clientBean1.logic();
    assertThat(count1).isEqualTo(1);

    // 클라이언트 요청2
    ClientPrototypeBean clientBean2 = ac.getBean(ClientPrototypeBean.class);
    int count2 = clientBean2.logic();
    assertThat(count2).isEqualTo(1);
  }

  // 2. 싱글톤 빈을 이용하여 스프링 컨테이너에 요청
  @Test
  void singletonClientUserPrototype() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

    ClientBean clientBean1 = ac.getBean(ClientBean.class);
    int count1 = clientBean1.logic();
    assertThat(count1).isEqualTo(1);

    ClientBean clientBean2 = ac.getBean(ClientBean.class);
    int count2 = clientBean2.logic();
    assertThat(count2).isEqualTo(2);
  }

  // 3. 싱글톤 빈을 이용하여 스프링 컨테이너에 요청 후 ObjectProvider 이용해서 새로 생성
  @Test
  void singletonClientUserObjectProvider() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBeanObjectProvider.class, PrototypeBean.class);

    ClientBeanObjectProvider clientBean1 = ac.getBean(ClientBeanObjectProvider.class);
    int count1 = clientBean1.logic();
    assertThat(count1).isEqualTo(1);

    ClientBeanObjectProvider clientBean2 = ac.getBean(ClientBeanObjectProvider.class);
    int count2 = clientBean2.logic();
    assertThat(count2).isEqualTo(1);
  }

  // 4. 싱글톤 빈을 이용하여 스프링 컨테이너에 요청 후 Provider 이용해서 새로 생성
  @Test
  void singletonClientUserProvider() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        ClientBeanProvider.class, PrototypeBean.class);

    ClientBeanProvider clientBean1 = ac.getBean(ClientBeanProvider.class);
    int count1 = clientBean1.logic();
    assertThat(count1).isEqualTo(1);

    ClientBeanProvider clientBean2 = ac.getBean(ClientBeanProvider.class);
    int count2 = clientBean2.logic();
    assertThat(count2).isEqualTo(1);
  }


  @Scope("prototype")
  static class ClientPrototypeBean{
    private final PrototypeBean prototypeBean;

    public ClientPrototypeBean(PrototypeBean prototypeBean) {
      this.prototypeBean = prototypeBean;
    }

    public int logic() {
      prototypeBean.addCount();
      return prototypeBean.getCount();
    }
  }

  @Scope("singleton")
  static class ClientBean{
    private final PrototypeBean prototypeBean;

    public ClientBean(PrototypeBean prototypeBean) {
      this.prototypeBean = prototypeBean;
    }

    public int logic() {
      prototypeBean.addCount();
      return prototypeBean.getCount();
    }
  }

  @Scope("singleton")
  static class ClientBeanObjectProvider{
    @Autowired
    private ObjectProvider<PrototypeBean> prototypeBeanProvider;
    public int logic() {
      PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
      prototypeBean.addCount();
      return prototypeBean.getCount();
    }
  }

  static class ClientBeanProvider{
    @Autowired
    private Provider<PrototypeBean> prototypeBeanProvider;

    public int logic() {
      PrototypeBean prototypeBean = prototypeBeanProvider.get();
      prototypeBean.addCount();
      int count = prototypeBean.getCount();
      return count;
    }
  }

  @Scope("prototype")
  static class PrototypeBean{
    private int count = 0 ;

    public void addCount() {
      count ++;
    }

    public int getCount() {
      return count;
    }

    @PostConstruct
    public void init() {
      System.out.println("PrototypeBean.init = " + this);
    }

    @PreDestroy
    public void destroy() {
      System.out.println("PrototypeBean.destroy = " + this);
    }
  }
}
