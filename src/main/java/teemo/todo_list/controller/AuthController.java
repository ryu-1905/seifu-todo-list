package teemo.todo_list.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import teemo.todo_list.model.Account;
import teemo.todo_list.service.AccountService;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthController {

    final AccountService accountService;

    @GetMapping("/login")
    public String getLoginPage(Model model, @RequestParam(required = false) Boolean error) {
        if (error != null && error) {
            model.addAttribute("error", "Invalid username or password.");
        }

        return "login";
    }

    @GetMapping("/register")
    public String GetRegisterPage(Model model, @RequestParam(required = false) Boolean error) {
        if (error != null && error) {
            model.addAttribute("error", "Email already in use");
        }

        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Account account) {
        try {
            accountService.register(account.getUsername(), account.getEmail(), account.getPassword());
        } catch (IllegalStateException e) {
            return "redirect:/register?error=true";
        }

        return "redirect:/login";
    }

    @GetMapping("/profile/{id}")
    public String getProfile(@PathVariable Long id, Model model) {
        model.addAttribute("account", accountService.getCurrentAccount());
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute Account account) {
        accountService.updateAccount(account);
        return "redirect:/";
    }
}
