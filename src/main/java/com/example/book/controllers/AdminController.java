package com.example.book.controllers;

import com.example.book.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("users", userService.list());
        return "admin";
    }

    @PostMapping("/admin/user/ban/{id}")
    public String userBan(@PathVariable("id") Long id) {
        userService.banUser(id);
        return "redirect:/admin";
    }

//    @GetMapping("/admin/user/edit/{user}")
//    public String userEdit(@PathVariable("user")User user, Model model) {
//        model.addAttribute("user", user);
//        model.addAttribute("roles", Role.values());
//
//        return "u";
//    }

//    @PostMapping("/admin/user/edit")
//    public String userEdit(@RequestParam("userId") Long id, @RequestParam(value = "roles", required = false) List<String> roles) {
//        User user = userService.findUserById(id);
//        userService.changeUserRoles(user, roles);
//        return "redirect:/admin";
//    }
}
