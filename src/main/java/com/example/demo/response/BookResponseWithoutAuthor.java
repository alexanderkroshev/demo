package com.example.demo.response;

import com.example.demo.model.Book;
import lombok.Data;

@Data
public class BookResponseWithoutAuthor {

    private final Long id;
    private final String bookName;
    private final Boolean isAvailable;
    private final int ordinationNumber;

    public static BookResponseWithoutAuthor fromBook(Book book) {
        return new BookResponseWithoutAuthor(book.getId(), book.getBookName(),
                book.isAvailable(), book.getOrdinationNumber());
          }
}

