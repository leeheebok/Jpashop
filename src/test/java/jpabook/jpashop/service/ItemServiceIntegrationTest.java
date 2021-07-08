package jpabook.jpashop.service;

import jpabook.jpashop.item.Book;
import jpabook.jpashop.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceIntegrationTest {

    @Autowired ItemRepository itemRepository;
    @Autowired ItemService itemService;
    @Autowired EntityManager em;

    @Test
    public void saveItem() {
        //given
        Book book = new Book();
        book.setPrice(1000);
        book.setName("book1");
        book.setStockQuantity(30);

        itemService.saveItem(book);

        assertThat(itemRepository.findOne(book.getId())).isEqualTo(book);
    }

    @Test
    public void updateItem() {
        //given
        Book book = new Book();
        book.setPrice(1000);
        book.setName("book1");
        book.setStockQuantity(30);

        itemRepository.save(book);

        //when
        itemService.updateItem(book.getId(), book);

        //then
        Item findbook = itemService.findOne(book.getId());
        assertThat(findbook.getName()).isEqualTo("book1");

    }

    @Test
    public void findItems() {
        //given
        Book book1 = new Book();
        book1.setName("book1");

        Book book2 = new Book();
        book2.setName("book2");

        itemRepository.save(book1);
        itemRepository.save(book2);

        //when
        List<Item> items = itemService.findItems();

        //then
        assertThat(items).hasSize(2);
    }

    @Test
    public void findOne() {
        //given
        Book book = new Book();
        book.setName("Lee");

        itemRepository.save(book);

        //when
        Item findone = itemService.findOne(book.getId());

        //then
        assertThat(findone.getId()).isEqualTo(book.getId());
        assertThat(findone.getName()).isEqualTo(book.getName());
    }
}