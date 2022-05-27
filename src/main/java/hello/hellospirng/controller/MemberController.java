package hello.hellospirng.controller;

import hello.hellospirng.domain.Member;
import hello.hellospirng.service.Memberservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
        // 2-2].SpringConfig클래스를 만들고 n@Configuration 어노테이션을 단다.
        // 2-3].그안에 @Bean어노테이션을단 MemberService객체를 반환하는, MemoryMemberRepoistory를 반환하는 함수를 만든다.
    }

    @GetMapping(value = "/members/new")
    public String createform(){
            return "members/createform";
    }
    @PostMapping(value = "/members/new")
    public String memberjoin(Memberform memberform)
    { //포스트를 받은것을 처리 컨트롤러패키지안에 버튼에 이름이같은 객체를 찾아 거기 넣는다
      //여기서는 Memberform의 name에 넣음
        Member member = new Member();
        member.setName(memberform.getName());
        memberservice.join(member);
        return "redirect:/";
    }
    @GetMapping(value = "/members")
    public String list(Model model)
    {
        List<Member> members = memberservice.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }


}
