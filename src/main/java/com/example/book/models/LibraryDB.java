package com.example.book.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@Table(name = "books")
public class LibraryDB {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long BookID;

    private String Title;

    private String Author;

    private String Category;

    private Long YearPublished;

    public LibraryDB() {
    }

    public Long getId() {
        return BookID;
    }

public void setId(Long BookID) {
    this.BookID = BookID;
}
}




