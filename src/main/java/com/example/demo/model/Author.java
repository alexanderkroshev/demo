package com.example.demo.model;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Data
@Entity
public class Author implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String firstName;

    private String secondName;

    private String patronymic;

    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date birthDay;

    private String biography;

    @ManyToMany(targetEntity = Book.class, mappedBy = "authorList", cascade = {MERGE, PERSIST, REFRESH})
    private List<Book> bookList;
}
