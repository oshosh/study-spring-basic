package hello.core.beandefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanDefinitionTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
//  GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");

  /*
  * BeanDefinition 통해서 xml이든 AnnotationConfig 설정을 통해서 다양한 통로로 빈을 설정하는 걸 확인 할 수 있음
  * */
  @Test
  @DisplayName("빈 설정 메타정보 확인")
  void findApplicationBean() {
    String[] beanDefinitionNames = ac.getBeanDefinitionNames();

    for (String beanDefinitionName : beanDefinitionNames) {
      BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

      if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
        System.out.println(
            "beanDefinitionName = " + beanDefinitionName + " beanDefinition = " + beanDefinition);
      }
    }
  }
}
