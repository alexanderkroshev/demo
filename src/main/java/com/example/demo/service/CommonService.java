package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.model.Author;
import com.example.demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommonService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public CommonService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public void addAuthor(Author author) {
        authorRepository.save(author);
    }

    public List<Book> findAllBooks(Optional<String> sortBy) {
        return bookRepository.findAll(Sort.by(Sort.Direction.ASC, sortBy.orElse("bookName")));
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }


    public List<Book> findAllBooksByAuthorId(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author not found on :: " + id));
        return bookRepository.findBooksByAuthorId(author.getId());
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    public Optional<Book> findBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Optional<Author> findAuthorById(Long id) {
        return authorRepository.findById(id);
    }

}


