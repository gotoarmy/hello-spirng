package hello.hellospirng.service;

import hello.hellospirng.domain.Member;
import hello.hellospirng.repository.MemberRepository;
import hello.hellospirng.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        member1.setName("Hello");
        //when
        Long ID = memberservice.join(member1);
        //then
        Member findMem =REPO.findById(ID).get();
        assertEquals(member1.getName(),findMem.getName());
    }

    @Test
    public void findMembers() {
    }

    @Test
    public void findOne() {
    }
}