package jpabook.jpashop.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import static org.assertj.core.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class MemberControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void createForm() throws Exception {
        //given
        MockHttpServletRequestBuilder request = get("/members/new");

        //when
        ResultActions perform = mockMvc.perform(request).andDo(print());

        //then
        perform.andExpect(status().isOk())
                .andExpect(model().attributeExists("memberForm"))
                .andExpect(view().name("members/createMemberForm"));
    }

    @Test
    public void create() throws Exception {
        //given
        MockHttpServletRequestBuilder createMemberRequest = post("/members/new")
                                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                        .param("name", "lee")
                                        .param("city", "서울")
                                        .param("street", "마포구")
                                        .param("zipcode", "222-333");

        ResultActions resultActions = mockMvc.perform(createMemberRequest).andDo(print());

        resultActions.andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/"));

    }

    @Test
    public void list() throws Exception {
        //given
        MockHttpServletRequestBuilder request = get("/members");

        ResultActions resultActions = mockMvc.perform(get("/members")).andDo(print());

        resultActions.andExpect(status().isOk())
                .andExpect(model().attributeExists("members"))
                .andExpect(view().name("members/memberList"));
    }
}