package hello.core.web;

import hello.core.common.MyLogger;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
  private final LogDemoService logDemoService;
  private final MyLogger myLogger;

  @RequestMapping("log-demo")
  @ResponseBody
  public String logDemo(HttpServletRequest request) {
    String requestURL = request.getRequestURI().toString();

    System.out.println("myLogger = " + myLogger.getClass()); // myLogger = class hello.core.common.MyLogger$$EnhancerBySpringCGLIB$$8912c8a0 껍데기..
    myLogger.setRequestURL(requestURL);

    myLogger.log("controller test");
    logDemoService.logic("testId");
    return "OK";
  }
}


//@Controller
//@RequiredArgsConstructor
//public class LogDemoController {
//  private final LogDemoService logDemoService;
//  private final ObjectProvider<MyLogger> myLoggerProvider;
//
//  @RequestMapping("log-demo") // http://localhost:8080/log-demo
//  @ResponseBody
//  public String logDemo(HttpServletRequest request) {
//    MyLogger myLogger = myLoggerProvider.getObject();
//    String requestURL = request.getRequestURI().toString();
//    myLogger.setRequestURL(requestURL);
//
//    myLogger.log("controller test");
//    logDemoService.logic("testId");
//    return "OK";
//  }