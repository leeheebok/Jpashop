package jpabook.jpashop.service;

import jpabook.jpashop.item.Book;
import jpabook.jpashop.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {

    @InjectMocks
    ItemService itemService;

    @Mock
    ItemRepository itemRepository;

    @Test
    public void saveItem() {
        Book book = new Book();
        book.setPrice(1000);
        book.setName("book1");
        book.setStockQuantity(30);

        itemService.saveItem(book);

        then(itemRepository).should().save(any(Item.class));
    }

    @Test
    public void updateItem() {
        //given
        Book book = new Book();
        book.setId(1L);
        book.setName("spring recipe");
        book.setPrice(10000);
        book.setStockQuantity(120);

        Book bookParam = new Book();
        bookParam.setPrice(12000);
        bookParam.setName("spring 5 recipe");
        bookParam.setStockQuantity(240);

        given(itemRepository.findOne(1L)).willReturn(book);

        //when
        Item findItem = itemService.updateItem(1L, bookParam);

        //then
        then(itemRepository).should().findOne(1L);
        assertThat(findItem.getPrice()).isEqualTo(bookParam.getPrice());
        assertThat(findItem.getName()).isEqualTo(bookParam.getName());
        assertThat(findItem.getStockQuantity()).isEqualTo(bookParam.getStockQuantity());


    }

    @Test
    public void findItems() {
        //given
        Book book1 = new Book();
        book1.setName("Lee");

        Book book2 = new Book();
        book2.setName("Hee");

        itemRepository.save(book1);
        itemRepository.save(book2);

        //when
        List<Item> finditems = itemService.findItems();
        //then
        assertThat(itemRepository.findAll());

    }

    @Test
    public void findOne() {
        //given
        Book book = new Book();
        book.setName("Lee");
        book.setId(1L);

        given(itemRepository.findOne(1L)).willReturn(book);

        //when
        Item findItem = itemService.findOne(1L);

        //then
        then(itemRepository).should().findOne(1L);
        assertThat(findItem.getId()).isEqualTo(1L);

    }
}