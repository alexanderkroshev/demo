package com.example.demo.response;

import com.example.demo.model.Author;
import lombok.Data;

import java.util.Date;

@Data
public class AuthorResponse {

    private final Long id;
    private final String biography;
    private final Date birthDay;
    private final String firstName;
    private final String secondName;
    private final String patronymic;



    public static AuthorResponse fromAuthor(Author author) {
        return new AuthorResponse(author.getId(), author.getBiography(),
                author.getBirthDay(), author.getFirstName(), author.getSecondName(),
                author.getPatronymic());
    }

}
