package com.example.demo.controller;


import com.example.demo.response.BookResponse;
import com.example.demo.response.BookResponseWithoutAuthor;
import com.example.demo.model.Author;
import com.example.demo.service.CommonService;
import com.example.demo.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private final CommonService commonService;

    @Autowired
    public BookController(CommonService commonService) {
        this.commonService = commonService;
    }

    @GetMapping
    public List<BookResponse> findAllBooks(@RequestParam Optional<String> sortBy) {
        return commonService.findAllBooks(sortBy).stream().
                map(BookResponse::fromBook).collect(Collectors.toList());
    }

    @GetMapping("by_author/{id}")
    public List<BookResponseWithoutAuthor> findAllBooksByAuthorId(@PathVariable("id") Long id) {
        Author author = commonService.findAuthorById(id).
                orElseThrow(() -> new ResourceNotFoundException("Author not found with id = " + id));
        return commonService.findAllBooksByAuthorId(id).stream()
                .map((BookResponseWithoutAuthor::fromBook)).collect(Collectors.toList());
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable("id") Long id) {
        Book book = commonService.findBookById(id).orElseThrow(() ->
                new ResourceNotFoundException("Book not found on :: " + id));
        commonService.deleteBookById(book.getId());
    }

    @PostMapping("/create")
    public void addBook(@ModelAttribute Book book) {
        commonService.addBook(book);
    }

    @PutMapping("/update/{id}")
    public void updateBook(@PathVariable("id") Long id,
                           @ModelAttribute Book updateBook) {
        Book book = commonService.findBookById(id).orElseThrow(() ->
                new ResourceNotFoundException("Book not found with id = " + id));
        book.setBookName(updateBook.getBookName());
        book.setAvailable(updateBook.isAvailable());
        book.setOrdinationNumber(updateBook.getOrdinationNumber());
        book.setAuthorList(updateBook.getAuthorList());
        commonService.addBook(book);
    }
}
