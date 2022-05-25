package hello.hellospirng.controller;

import hello.hellospirng.service.Memberservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController { //맴버 서비스를 컨트롤하기위해 인젝션해주어야함
    private final Memberservice memberservice; //생성자에의한 컴포넌트 자동의존관계설정

    @Autowired            //오토와이어드가 있으면 스프링컨테이너가  컨트롤러와 연관된것들을 외부에서 넣어주는 것 DI
   public MemberController(Memberservice memberservice) {
        this.memberservice = memberservice;
        // memberservice빨간줄 오류 ->스프링빈 등록오류
        //[컴포넌트 스캔,자동의존관계설정]
        // 1.memverservice,Repo클래스에다가 어노테이션을 달아서 스프링패키지안에서 스프링컨테이너가 인식하게함
        // [자바코드로 직접 스프링 빈 등록하기] -->요것이 나중에 변경사항이 생길 때 대처가 쉽다.
        // 2-1].오토와이어드와 레포서비스 어노테이션을 제거한다.
        // 2-2].SpringConfig클래스를 만들고 @Configuration 어노테이션을 단다.
        // 2-3].그안에 @Bean어노테이션을단 MemberService객체를 반환하는, MemoryMemberRepoistory를 반환하는 함수를 만든다.
    }
}
