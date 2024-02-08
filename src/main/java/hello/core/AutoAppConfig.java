package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
//    basePackages = "hello.core.member", // 해당하는 패키지에서 부터 하위를 조회를 함
//    basePackageClasses = AutoAppConfig.class, // 해당 영역의 클래스의 패키지 첫 선언 부터 전부 조회 현재는 package hello.core; 이거니 전부 조회함
    // default 조회는 현재 AutoAppConfig 에서 설정한 package hello.core 부터 시작 하니 전부 조회함
    excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
