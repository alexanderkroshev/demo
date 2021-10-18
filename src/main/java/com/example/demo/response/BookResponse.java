package com.example.demo.response;

import com.example.demo.model.Book;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class BookResponse {

    private final Long id;
    private final String bookName;
    private final Boolean isAvailable;
    private final int ordinationNumber;
    private final List<AuthorResponse> authorResponseList;


    public static BookResponse fromBook(Book book) {
        List<AuthorResponse> authorResponses = book.getAuthorList().stream()
                .map(AuthorResponse::fromAuthor).collect(Collectors.toList());
        return new BookResponse(book.getId(), book.getBookName(), book.isAvailable(),
                book.getOrdinationNumber(), authorResponses);
    }
}
