package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class MemberControllerTest {

    @InjectMocks
    MemberController memberController;

    @Mock
    MemberService memberService;

    MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }

    @Test
    public void createForm() throws Exception {
        mockMvc.perform(get("/members/new"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("memberForm"))
                .andExpect(view().name("members/createMemberForm"));
    }

    @Test
    public void create() throws Exception {
        //given
        MockHttpServletRequestBuilder request = createJoinRequest();

        //when
        ResultActions resultActions = mockMvc.perform(request).andDo(print());

        //then

        resultActions.andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void list() throws Exception {

        //given
        Member member = new Member();
        member.setName("lee");
        Member anotherMember = new Member();
        anotherMember.setName("pyo");
        given(memberService.findMembers()).willReturn(List.of(member, anotherMember));

        MockHttpServletRequestBuilder request = get("/members");
        //when
        ResultActions resultActions = mockMvc.perform(request).andDo(print());


        //then
        resultActions.andExpect(status().isOk())
                .andExpect(model().attributeExists("members"))
                .andExpect(view().name("members/memberList"));

        then(memberService).should().findMembers();

        List<Member> members = (List<Member>) resultActions.andReturn().getModelAndView().getModel().get("members");
        assertThat(members).hasSize(2);
        assertThat(members).contains(member, anotherMember);
    }

    public MockHttpServletRequestBuilder createJoinRequest() {
         return post("/members/new")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "lee")
                .param("city", "서울")
                .param("street", "마포구")
                .param("zipcode", "222-333");
    }

}