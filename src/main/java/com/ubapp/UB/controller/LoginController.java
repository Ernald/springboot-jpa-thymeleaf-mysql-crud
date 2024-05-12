package com.ubapp.UB.controller;

import com.ubapp.UB.model.Contacts;
import com.ubapp.UB.model.User;
import com.ubapp.UB.repository.ContactRepository;
import com.ubapp.UB.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ContactRepository contactRepository;

    //Without Authentication we can not access home page
//    @GetMapping("/")
//    public String home(Authentication authentication) {
//        if (authentication == null || !authentication.isAuthenticated()) {
//            return "redirect:/login-required";
//        }
//        return "home";
//    }


    //Access page login
    @GetMapping("/")
    public String showLoginForm() {
        return "login";
    }

    //Try to open home page from signin
    @PostMapping("/")
    public String login(@RequestParam String username, @RequestParam String password) {
        // Retrieve user from database
        User user = userRepository.findByUsername(username);

        // Check if user exists and password matches
        if (user != null && user.getPassword().equals(password)) {
            // Redirect to home page
            return "redirect:/home";
        } else {
            // Handle login failure
            return "redirect:/login?error";
        }
    }

    //Access page signup
    @GetMapping("/signup")
    public String showSignupForm() {
        return "signup";
    }

    //Save data into database
    @PostMapping("/signup")
    public String signup(@RequestParam String username, @RequestParam String password) {
        // Check if user already exists
        if (userRepository.findByUsername(username) != null) {
            // Handle error: User already exists
            return "redirect:/signup?error";
        }

        // Create new user
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        // Save user to database
        userRepository.save(user);

        // Redirect to login page
        return "redirect:/";
    }

    //Access home
    @GetMapping("/home")
    public String showHomePage(Model model) {
        model.addAttribute("contact", new Contacts());
        return "home";
    }
}
