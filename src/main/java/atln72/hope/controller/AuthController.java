package atln72.hope.controller;

import atln72.hope.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        try {
            String token = authService.login(username, password);
            return ResponseEntity.ok(token); // Renvoie le token si la connexion réussit
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage()); // Sinon, renvoie une erreur
        }
    }
}
