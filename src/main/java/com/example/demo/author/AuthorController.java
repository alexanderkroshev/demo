package com.example.demo.author;

import com.example.demo.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final CommonService commonService;

    @Autowired
    public AuthorController(CommonService commonService) {
        this.commonService = commonService;
    }

    @PostMapping("/create")
    public void addAuthor(@ModelAttribute Author author) {
        commonService.addAuthor(author);
    }

    @PutMapping("/update/{secondName}")
    public void updatePerson(@PathVariable String secondName,
                             @RequestParam("firstName") String firstName,
                             @RequestParam("newSecondName") String newSecondName,
                             @RequestParam("patronymic") String patronymic,
                             @RequestParam("biography") String biography,
                             @RequestParam("date") Date date,
                             @RequestParam("bookList") List<String> bookNameList
    ) {
        Author author = commonService.findAuthorBySecondName(secondName);
        author.setFirstName(firstName);
        author.setSecondName(newSecondName);
        author.setPatronymic(patronymic);
        author.setBiography(biography);
        author.setDate(date);

        List<Book> bookList = new ArrayList<>();
        Book book;
        for (String i : bookNameList) {
            book = commonService.findBookByBookName(i);
            bookList.add(book);
        }
        author.setBookList(bookList);
        commonService.addAuthor(author);
    }
}