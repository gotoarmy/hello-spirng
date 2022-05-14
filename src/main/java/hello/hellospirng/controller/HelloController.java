package hello.hellospirng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")  //"hello"라는 키값을 받으면 이 함수값을 넘겨줌
    public String hello(Model model) //모델?  mvc의 모델 뷰 컨트롤러의 모델
    {
        //인자인 모델을 받아 속성을 추가한다
        model.addAttribute("data", "Spring!");
        return "hello";  // -->리소스파일 템플릿에 hello.html을 추가했으면 글로 보내줄게.
        //컨트롤러에서 리턴 값으로 문자를 반환하면 viewResolver가 templates폴더에서 hello.html찾아서 처리함
        //기본적인 viewname 매핑 resources:templates/+{viewName}+.html
    }

    @GetMapping("hello-mvc") //hello-mvc를 주소창에 치면
    public String hellomvc(@RequestParam("name") String name, Model model) //네임을 추가해줘야 동작이됨,인자를 요구한다.
    {//hello-mvc?name=spring 이라 넣어 줄 수 있음
        model.addAttribute("name", name); //"name이 키
        return "hello-template";
    }

    @GetMapping("hello-string")  //api방법
    @ResponseBody //http포로토콜에서도  헤드부와 바디부가 있다.그 바디부에 내가 직접 데이터를 넣겠다.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;//네임 인자를 설정하는 법 , 뷰리졸버,html을 거치지  html파일을 만들지 않고 그냥출력
    }
    //만약에 데이터를 내놓으라고 한다면? 객체를 넘기자, json으로 넘겨줌
    //커멘드 쉬프트 앤터 완성해줌

    static class Hello {
        private String name;
       //alt +insert

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    @GetMapping("hello-api") //httpmessageconverter -> 객체면 json스타일로 ,문자는 문자로 브라우저로 보내줌
    @ResponseBody  //제이슨으로 반환하는게 현 시점에서는 json이 원칙,xml방식을 사용한다면 나중에 사용
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

}
