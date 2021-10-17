package com.example.demo.author;

import com.example.demo.book.Book;
import com.example.demo.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    public Author findAuthorBySecondName(String secondName) {
        return authorRepository.findAuthorBySecondName(secondName);
    }

    public List<Book> findAllBooks(Optional<String> sortBy) {
        return bookRepository.findAll(Sort.by(Sort.Direction.ASC, sortBy.orElse("bookName")));
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public void deleteBook(String bookName) {
        Book book = bookRepository.findBookByBookName(bookName);
        bookRepository.deleteById(book.getId());
    }

    public Book findBookByBookName(String bookName) {
        return bookRepository.findBookByBookName(bookName);
    }

    public List<Book> findAllBooksByAuthor(String authorSecondName) {
        Author author= authorRepository.findAuthorBySecondName(authorSecondName);
        return bookRepository.findBooksByAuthorId(author.getId());
    }




}


