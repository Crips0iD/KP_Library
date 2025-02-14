package com.example.book.controllers;

import java.security.Principal;
import java.util.List;

import com.example.book.models.LibraryDB;
import com.example.book.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/books")
public class AppController {
    @Autowired
    private BookService server;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String vieHomePage(Model model, @RequestParam(name = "keyword", required = false) String keyword, Principal principal) {
        System.out.println("Keyword: " + keyword);
        List<LibraryDB> listBooks = server.listAll(keyword);
        model.addAttribute("listBooks", listBooks);
        model.addAttribute("keyword", keyword);
        model.addAttribute("user", server.getUserByPrincipal(principal));
        return "index";

    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String showNewBookForm(Model model) {
        LibraryDB LibraryDB = new LibraryDB();
        model.addAttribute("LibraryDB", LibraryDB);
        return "new_book";
    }

    /*@RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveBook(@ModelAttribute("LibraryDB") LibraryDB LibraryDB) {
        server.save(LibraryDB);
        return "redirect:/books/";
    }*/
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveBook(@ModelAttribute("LibraryDB") LibraryDB LibraryDB) {
        System.out.println("Сохраняем книгу: " + LibraryDB);
        server.save(LibraryDB);
        return "redirect:/books/";
    }


    @RequestMapping(value = "/edit/{BookID}", method = RequestMethod.GET)
    public ModelAndView showEditBookForm(@PathVariable(name = "BookID") Long BookID) {
        ModelAndView mav = new ModelAndView("edit_Book");
        LibraryDB LibraryDB = server.get(BookID);
        mav.addObject("LibraryDB", LibraryDB);
        return mav;
    }

    @RequestMapping(value = "/delete/{BookID}", method = RequestMethod.GET)
    public String daleteLibraryDB(@PathVariable(name = "BookID") Long BookID) {
        server.delete(BookID);
        return "redirect:/books/";
    }


}
