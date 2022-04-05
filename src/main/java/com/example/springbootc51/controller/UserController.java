package com.example.springbootc51.controller;

import com.example.springbootc51.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.springbootc51.repository.UserRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String home() {
        if (userRepository.findById(1L).isEmpty()) {
            userRepository.save(new User("test1", "test1"));
            userRepository.save(new User("test2", "test2"));
            userRepository.save(new User("test3", "test3"));
        }
        return "index";
    }

    @GetMapping("/reg")
    public String reg(@ModelAttribute("user") User user) {
        return "reg";
    }

    @PostMapping("/reg")
    public String reg(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "reg";
        } else if (userRepository.findByName(user.getName()).isPresent()) {
            model.addAttribute("messageUserExist", "User exist");
            return "reg";
        }
        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute("user") User user) {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                        HttpSession session, Model model) {
        if (bindingResult.hasErrors()) {
            return "login";
        } else if (userRepository.findByName(user.getName()).isPresent()) {
            User userFromDB = userRepository.findByName(user.getName()).get();
            if (userFromDB.getPassword().equals(user.getPassword())) {
                session.setAttribute("user", userFromDB);
            } else {
                model.addAttribute("messageUserExist", "Invalid user/password");
                return "login";
            }
        } else {
            model.addAttribute("messageUserExist", "User does not exist");
            return "login";
        }
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        } else {
            session.invalidate();
        }
        return "redirect:/";
    }





    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();
            model.addAttribute("user", user);
        }
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        session.setAttribute("user", user);
        userRepository.save(user);
        return "index";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id, HttpSession session) {
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();
            userRepository.delete(user);
            session.invalidate();
        }
        return "index";
    }

}
