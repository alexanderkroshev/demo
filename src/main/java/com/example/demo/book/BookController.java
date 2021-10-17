package com.example.demo.book;


import com.example.demo.author.Author;
import com.example.demo.author.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    private final CommonService commonService;

    @Autowired
    public BookController(CommonService commonService) {
        this.commonService = commonService;
    }

    @GetMapping
    public List<Book> findAllBooks(@RequestParam Optional<String> sortBy) {
        return commonService.findAllBooks(sortBy);
    }

    @GetMapping("by_author/{authorSecondName}")
    public List<Book> findAllBooksByAuthor(@PathVariable("authorSecondName") String authorSecondName) {
        return commonService.findAllBooksByAuthor(authorSecondName);
    }

    @DeleteMapping("/delete/{bookName}")
    public void deleteBook(@PathVariable("bookName") String bookName) {
        commonService.deleteBook(bookName);
    }

    @PostMapping("/create")
    public void addBook(@ModelAttribute Book book) {
        commonService.addBook(book);
    }

    @PutMapping("/update/{bookName}")
    public void updatePerson(@PathVariable String bookName,
                             @RequestParam("newBookName") String newBookName,
                             @RequestParam("ordinationNumber") int ordinationNumber,
                             @RequestParam("isAvailable") Boolean isAvailable,
                             @RequestParam("authorList") List<String> authorNameList) {
        Book book = commonService.findBookByBookName(bookName);
        book.setBookName(newBookName);
        book.setOrdinationNumber(ordinationNumber);
        book.setAvailable(isAvailable);

        List<Author> authorList = new ArrayList<>();
        Author author;
        for (String i : authorNameList) {
            author = commonService.findAuthorBySecondName(i);
            authorList.add(author);
        }
        book.setAuthorList(authorList);
        commonService.addBook(book);
    }
}
