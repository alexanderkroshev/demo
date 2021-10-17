package com.example.demo.author;


import com.example.demo.book.Book;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Author implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String firstName;

    private String secondName;

    private String patronymic;

    @Temporal(TemporalType.DATE)
    Date date;

    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date birthDay;

    private String biography;

    @ManyToMany(targetEntity = Book.class, mappedBy = "authorList", cascade = CascadeType.ALL)
    private List<Book> bookList;

}
