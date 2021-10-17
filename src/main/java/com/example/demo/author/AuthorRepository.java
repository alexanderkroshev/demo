package com.example.demo.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Transactional(readOnly = true)
    @Query(value ="SELECT * FROM author where second_name=:secondName", nativeQuery = true)
    Author findAuthorBySecondName(@Param("secondName") String secondName);
}
