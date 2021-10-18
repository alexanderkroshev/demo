package com.example.demo.repository;

import com.example.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Long> {

    @Transactional(readOnly = true)
    @Query(value = "select * from book where id IN(select book_list_id from book_author_list where author_list_id =:id)", nativeQuery = true)
    List<Book> findBooksByAuthorId(@Param("id") Long id);

}
