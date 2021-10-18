package com.example.demo.controller;

import com.example.demo.service.CommonService;
import com.example.demo.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/update/{id}")
    public void updateAuthor(@PathVariable Long id,
                             @ModelAttribute Author updatedAuthor) {
        Author author = commonService.findAuthorById(id).
                orElseThrow(() -> new ResourceNotFoundException("Author not found with id = " + id));
        author.setFirstName(updatedAuthor.getFirstName());
        author.setSecondName(updatedAuthor.getSecondName());
        author.setPatronymic(updatedAuthor.getPatronymic());
        author.setBiography(updatedAuthor.getBiography());
        author.setBookList(updatedAuthor.getBookList());
        author.setBirthDay(updatedAuthor.getBirthDay());
        commonService.addAuthor(author);
    }
}