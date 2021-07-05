package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;
    @Autowired EntityManager em;

    @Test
//    @Rollback(value = false)
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("Lee");

        //when
        Long saveId = memberService.join(member);

        //then
        assertThat(memberRepository.findOne(saveId)).isEqualTo(member);
    }

    @Test
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("Lee");

        Member member2 = new Member();
        member2.setName("Lee");


        assertThatThrownBy(() -> {
            memberService.join(member1);
            memberService.join(member2);
        }).isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    public void 회원_수정_테스트() {
        //given
        String updateName = "Lee hee bok";

        Member member = new Member();
        member.setName("Lee");

        memberService.join(member);

        //when
        memberService.update(member.getId(), updateName);

        //then
        Member updatedMember = memberService.findOne(member.getId());
        assertThat(updatedMember.getName()).isEqualTo(updateName);

    }

    @Test
    public void 전체_회원_조회() {
        //given
        Member member1 = new Member();
        member1.setName("Lee");

        Member member2 = new Member();
        member2.setName("Pyo");

        memberService.join(member1);
        memberService.join(member2);

        //when
        List<Member> members = memberService.findMembers();

        //them
        assertThat(members).hasSize(2);
    }

}