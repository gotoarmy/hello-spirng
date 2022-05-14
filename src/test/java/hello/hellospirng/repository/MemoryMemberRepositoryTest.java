package hello.hellospirng.repository;

import hello.hellospirng.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
         MemoryMemberRepository Repo =new MemoryMemberRepository();
    @AfterEach
    public void aftereach()
    {
        Repo.clearStore();
    }

    @Test
    public void save() {
             Member mem3 =new Member();
        Member member2 = new Member();
        mem3.setName("홍금보");
        member2.setName("와칸다");
             Repo.save(mem3);
        Member result = Repo.findByName("홍금보").get(); //get 옵셔널로 받지 않기위해
        assertThat(result).isEqualTo(mem3);
    }

    @Test
    public void findById() {
        //given
        Member mem = new Member();
        Member mem2 = new Member();
        mem.setId(1l);
        mem.setName("그리피스");
        mem2.setId(3l);
        Repo.save(mem);
        Repo.save(mem2);
        //when
        Member result = Repo.findById(1l).get();
        //then
        assertThat(result).isEqualTo(mem);
    }

    @Test
    public void findByName() {
        //given
        Member mem5 = new Member();
        Member mem6 = new Member();
        mem5.setName("그리피스");
        mem6.setName("가츠");
        Repo.save(mem5);
        Repo.save(mem6);

        //when
        Member result = Repo.findByName("그리피스").get();

        //then
        assertThat(result).isEqualTo(mem5);

    }

    @Test
    public void findAll() {
        //given
        Member amem = new Member();
        Member amem2 = new Member();
        Member amem3 = new Member();
        Repo.save(amem);
        Repo.save(amem2);
        Repo.save(amem3);
        //when
        List<Member> result = Repo.findAll();
        //then
        assertThat(result.size()).isEqualTo(3);
    }


}