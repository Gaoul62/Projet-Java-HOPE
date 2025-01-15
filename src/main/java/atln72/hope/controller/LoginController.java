package atln72.hope.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import atln72.hope.model.UserAppEntity;
import atln72.hope.service.UserAppService;
import atln72.hope.util.UserContext;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {
    @Autowired
    private UserAppService userAppService;

    @GetMapping("/")
    public String defaultPage() {
        return "login";
    }
    

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model) {
        UserAppEntity user = userAppService.authenticate(username, password);
        if (user != null) {
            UserContext.setCurrentUser(user);
            return "redirect:/tools/show";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
}
