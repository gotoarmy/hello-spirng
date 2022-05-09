package hello.hellospirng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")  //"hello"라는 키값을 받으면 이 함수값을 넘겨줌
    public String hello(Model model) //모델?  mvc의 모델 뷰 컨트롤러의 모델
    {
        //인자인 모델을 받아 속성을 추가한다
        model.addAttribute("data","hello");
        return "hello";  // -->리소스파일 템플릿에 hello.html을 추가했으면 글로 보내줄게.
        //컨트롤러에서 리턴 값으로 문자를 반환하면 viewResolver가 templates폴더에서 hello.html찾아서 처리함
        //기본적인 viewname 매핑 resources:templates/+{viewName}+.html
    }
}
