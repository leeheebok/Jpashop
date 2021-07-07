package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class MemberServiceTest {

    @InjectMocks
    MemberService memberService;

    @Mock
    MemberRepository memberRepository;

    @Test
    public void 회원_가입() {
        //given
        Member member = new Member();
        member.setName("Lee");
        given(memberRepository.findByName(member.getName())).willReturn(Collections.emptyList());

        //when
        memberService.join(member);

        //then
        then(memberRepository).should().findByName(member.getName());
        then(memberRepository).should().save(member);

    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member = new Member();
        member.setName("Lee");
        given(memberRepository.findByName(member.getName())).willReturn(List.of(member));

        //when
        assertThatThrownBy(() -> {
            memberService.join(member);
        }).isInstanceOf(IllegalArgumentException.class);

        then(memberRepository).should(never()).save(member);
    }


    @Test
    public void findMembers() {
        //given
        Member member1 = new Member();
        member1.setName("Lee");

        Member member2 = new Member();
        member2.setName("Hee");

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> findMembers = memberService.findMembers();

        //then
        assertThat(memberRepository.findAll());
    }

    @Test
    public void findOne() {
        //given
        Member member1 = new Member();
        member1.setName("Lee");
        member1.setId(1L);

        given(memberRepository.findOne(1L)).willReturn(member1);
        //when
        Member findMember = memberService.findOne(1L);

        //then
        then(memberRepository).should().findOne(1L);
        assertThat(findMember.getId()).isEqualTo(1L);
    }

    @Test
    public void update() {
       //given
        Member member = new Member();
        member.setId(1L);
        member.setName("Lee");

        given(memberRepository.findOne(1L)).willReturn(member);

        //when
        memberService.update(1L, "Lee hee bok");

        //then
        then(memberRepository).should().findOne(member.getId());
    }
}