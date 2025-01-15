package atln72.hope.service;

import atln72.hope.repository.UserAppRepository;
import atln72.hope.util.UserContext;
import atln72.hope.model.UserAppEntity;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private static final Logger logger = Logger.getLogger(StudentToolService.class.getName());
    @Autowired
    private UserAppRepository userAppRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public String login(String username, String password) {
        UserAppEntity user = userAppRepository.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            // Génère un token JWT pour l'utilisateur
            logger.log(Level.INFO, "User {0} logged in", user.getUsername());
            UserContext.setCurrentUser(user);
            return jwtUtil.generateToken(user.getUsername(), user.getUserRole());
        } else {
            logger.log(Level.WARNING, "Invalid username or password");
            throw new RuntimeException("Nom d'utilisateur ou mot de passe incorrect.");
        }
    }

    public boolean validateToken(String token) {
        return jwtUtil.validateToken(token);
    }

    public String extractRole(String token) {
        return jwtUtil.extractRole(token);
    }
}
