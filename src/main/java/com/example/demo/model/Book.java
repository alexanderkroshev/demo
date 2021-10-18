package com.example.demo.model;

import com.example.demo.model.Author;
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

    private boolean isAvailable;

    @ManyToMany(targetEntity = Author.class, cascade = {MERGE, PERSIST, REFRESH})
    private List<Author> authorList;


}
