package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.item.Book;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @InjectMocks
    OrderService orderService;
    
    @Mock
    OrderRepository orderRepository;
    
    @Test
    public void order() {
        //given
        createmember();

        createBook();


        //when
        
        //then
    }

    private void createmember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
    }

    private void createBook() {
        Book book = new Book();
        book.setName("JPA");
        book.setPrice(10000);
        book.setStockQuantity(10);
    }

    @Test
    public void cancelOrder() {
        //given
        
        //when
        
        //then
    }

    @Test
    public void findOrders() {
        //given
        
        //when
        
        //then
    }
}