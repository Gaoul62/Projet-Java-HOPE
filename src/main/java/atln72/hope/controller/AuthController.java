package atln72.hope.controller;

import atln72.hope.service.AuthService;
import atln72.hope.util.UserContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/")
    public String defaultPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        String token = authService.login(username, password);
        if (token == null) {
            return "redirect:/";
        }
        UserContext.setToken(token);
        session.setAttribute("token", token);
        return "redirect:/tools/show";
    }
}