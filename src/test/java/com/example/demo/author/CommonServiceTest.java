package com.example.demo.author;

import com.example.demo.book.Book;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CommonServiceTest {

    @Autowired
    protected CommonService service;

    private final String bookName ="3 musketera";


    @Test
    void addBook() {
        Author author = new Author();
        author.setSecondName("Duma");
        ArrayList<Author> authorList = new ArrayList();
        authorList.add(author);

        Book book = new Book();
        book.setBookName("bookName");
        book.setAvailable(true);
        book.setOrdinationNumber(3);
        book.setAuthorList(authorList);
        service.addBook(book);

        Book book2 = service.findBookByBookName("bookName");
        assertThat(book2.getBookName()).isEqualTo("bookName");
    }

    @Test
    void deleteBook() {
        service.deleteBook(service.findBookByBookName("bookName").getBookName());
        assertThat(this.service.findBookByBookName("bookName")).isNull();
    }
}