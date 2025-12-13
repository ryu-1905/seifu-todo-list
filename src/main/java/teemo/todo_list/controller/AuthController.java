package teemo.todo_list.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import teemo.todo_list.model.Account;
import teemo.todo_list.service.AccountService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthController {

    final AccountService accountService;

    @GetMapping("/login")
    public String login(Model model, @RequestParam(required = false) Boolean error) {
        if (error != null && error) {
            model.addAttribute("error", "Invalid username or password.");
        }

        return "login";
    }

    @GetMapping("/register")
    public String register(Model model, @RequestParam(required = false) Boolean error) {
        if (error != null && error) {
            model.addAttribute("error", "Email already in use");
        }

        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestBody Account account) {
        try {
            accountService.register(account.getUsername(), account.getEmail(), account.getPassword());
            return "redirect:/login";
        } catch (IllegalStateException e) {
            return "redirect:/register?error=true";
        }
    }
}
