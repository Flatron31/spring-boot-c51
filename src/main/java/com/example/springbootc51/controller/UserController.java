package com.example.springbootc51.controller;

import com.example.springbootc51.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.springbootc51.repository.UserRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping
    public String home() {
        if (userRepository.findById(1L) == null) {
            userRepository.save(new User("test1", "test1"));
            userRepository.save(new User("test2", "test2"));
            userRepository.save(new User("test3", "test3"));
        }
        return "templates/index";
    }

    @GetMapping("/reg")
    public String reg(@ModelAttribute("user") User user) {
        return "templates/reg";
    }

    @PostMapping("/reg")
    public String reg(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "templates/reg";
        }

        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute("user") User user){
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
                        HttpSession session, Model model){
        if (bindingResult.hasErrors()){
            return "user/login";
        }
        User userFromDB = new User();

//        if (userRepository.findByUsername(user.getName()) != null) {
//            Optional<User> userFromDB = userRepository.findByUsername(user.getName());
//            if (userFromDB.getPassword().equals(user.getPassword())) {
//                session.setAttribute("user", userFromDB);
//            }
//        } else {
//            model.addAttribute("messageError", "User exist");
//            return "user/login";
//        }
        return "user/index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        if (session.getAttribute("user") == null){
            return "redirect:/";
        } else {
            session.invalidate();
        }
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id){
        model.addAttribute("user", userRepository.findById(id));
        return "user/edit";
    }


    @PatchMapping("/{id}")
    public String update(@Valid @ModelAttribute("user") User user,BindingResult bindingResult, HttpSession session){
        if (bindingResult.hasErrors()){
            return "user/edit";
        }
        session.setAttribute("user", user);
        userRepository.save(user);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id, HttpSession session) {
       //////////////////////// обверткууууу


//
//        Optional<User> user = userRepository.findById(id);
//        User user = userRepository.findById(id);
//        userRepository.delete(user);
//        session.invalidate();
        return "redirect:/";
    }

}
