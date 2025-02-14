package com.example.book.repositories;

import java.util.List;

import com.example.book.models.LibraryDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<LibraryDB, Long> {

    @Query("SELECT p FROM LibraryDB p WHERE concat(p.BookID,'', p.Title,'', p.Author, '',p.Category, '',p.YearPublished, '') LIKE %?1%")
    List<LibraryDB> search(String keyword);

}
