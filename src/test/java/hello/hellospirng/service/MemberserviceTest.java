package hello.hellospirng.service;

import hello.hellospirng.domain.Member;
import hello.hellospirng.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MemberserviceTest {
    MemoryMemberRepository REPO;
    Memberservice memberservice;  // 전역변수 전방선언;
    @BeforeEach
    public void BeforeEach() //Di
    {
        REPO = new MemoryMemberRepository();
        memberservice = new Memberservice(REPO);
    }
    @AfterEach
    public void clearstone()
    {
        REPO.clearStore();
    }
    @Test
    public void join() {
        //given
        Member member1 = new Member();
        member1.setName("Drake");
        Member member2 = new Member();
        member2.setName("Drk");

        //when
        Long ID = memberservice.join(member1);
        memberservice.join(member2);
        //then
        Member findMem =REPO.findById(ID).get();
        assertEquals(member1.getName(),findMem.getName());
    }

    @Test
    public void findMembers() {
        //given
        Member member1 = new Member();
        member1.setName("강동훈");
        Member member2 = new Member();
        member2.setName("강명철");
        Member member3 = new Member();
        member3.setName("장진남");
        //when
        memberservice.join(member1);
        memberservice.join(member2);
        memberservice.join(member3);
        //then
        List<Member> listmember = memberservice.findMembers();
        assertEquals(listmember.size(),3);

    }

    @Test
    public void findOne() {
        //given
        Member member3 = new Member();
        member3.setName("장진남");
        Member member2 = new Member();
        member2.setName("장첸");

        //when
        memberservice.join(member3);
        memberservice.join(member2);
        Member result = memberservice.findOne(member3.getId()).get();
        //then
        Assertions.assertThat(result).isEqualTo(member3);

    }
}