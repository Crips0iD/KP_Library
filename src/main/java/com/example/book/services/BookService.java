package com.example.book.services;

import java.security.Principal;
import java.util.List;

import com.example.book.models.User;
import com.example.book.repositories.BookRepository;
import com.example.book.models.LibraryDB;
import com.example.book.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    @Autowired
    private BookRepository repo;

    private final UserRepository userRepository;

    public List<LibraryDB> listAll(String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            List<LibraryDB> books = repo.search(keyword);
            System.out.println("Found books: " + books);
            return repo.search(keyword);
        }
        return repo.findAll();
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    public void save(LibraryDB librarydb) {
        if (librarydb.getBookID() != null && repo.existsById(librarydb.getBookID())) {
            LibraryDB existingBook = repo.findById(librarydb.getBookID()).orElse(null);
            if (existingBook != null) {
                existingBook.setTitle(librarydb.getTitle());
                existingBook.setAuthor(librarydb.getAuthor());
                existingBook.setCategory(librarydb.getCategory());
                existingBook.setYearPublished(librarydb.getYearPublished());
                repo.save(existingBook);
            }
        } else {
            repo.save(librarydb);
        }
    }

    public LibraryDB get(Long BookID) {
        return repo.findById(BookID).orElse(null);
    }

    public void delete(Long BookID) {
        repo.deleteById(BookID);
    }
}
