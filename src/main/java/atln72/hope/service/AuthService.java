package atln72.hope.service;

import atln72.hope.repository.UserAppRepository;
import atln72.hope.model.UserAppEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserAppRepository userAppRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public String login(String username, String password) {
        UserAppEntity user = userAppRepository.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            // Génère un token JWT pour l'utilisateur
            return jwtUtil.generateToken(user.getUsername(), user.getUserRole());
        } else {
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
