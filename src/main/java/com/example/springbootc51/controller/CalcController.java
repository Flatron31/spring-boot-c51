package com.example.springbootc51.controller;

import com.example.springbootc51.entity.Operation;
import com.example.springbootc51.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.springbootc51.repository.OperationRepository;
import com.example.springbootc51.repository.UserRepository;
import com.example.springbootc51.service.OperationService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@Controller
public class CalcController {

    private OperationRepository operationRepository;
    private UserRepository userRepository;

    public CalcController(OperationRepository operationRepository, UserRepository userRepository) {
        this.operationRepository = operationRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/calc")
    public String calc(@ModelAttribute("operation") Operation operation) {
        return "calc";
    }

    @PostMapping("/calc")
    public String calc(@ModelAttribute("operation") @Valid Operation operation, BindingResult bindingResult,
                       HttpSession session, Model model) {
        if (bindingResult.hasErrors()) {
            return "calc";
        }
        Double res = OperationService.getResultOperation(operation);
        model.addAttribute("result", res);
        operation.setResult(res);;
        operationRepository.save(operation);
        User user = (User) session.getAttribute("user");
        List<Operation> operationList = user.getOperation();
        operationList.add(operation);
        user.setOperation(operationList);
        userRepository.save(user);
        return "calc";
    }

    @GetMapping("/history")
    public String history(@ModelAttribute("operation") Operation operation, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("history", user.getOperation());
        return "history";
    }
}
