package com.example.demo.book;

import com.example.demo.author.Author;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.CascadeType.*;


@Data
@Entity
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String bookName;

    private int ordinationNumber;
    //@NotEmpty
    private boolean isAvailable;

    //@NotEmpty
    @ManyToMany(targetEntity = Author.class, cascade = {MERGE, PERSIST, REFRESH})
    private List<Author> authorList;
}
