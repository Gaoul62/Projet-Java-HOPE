package atln72.hope.util;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.io.IOException;

@Service
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Récupère le header Authorization de la requête
        String authHeader = request.getHeader("Authorization");
        String token = null;

        // Vérifie si le header Authorization est présent et commence par "Bearer "
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7); // Récupère uniquement le token sans "Bearer "
        } else {
            // Retrieve token from session if not present in Authorization header
            token = (String) request.getSession().getAttribute("token");
        }

        if (token != null && jwtUtil.validateToken(token)) { // Valide le token
            // Récupère les informations depuis le token
            String username = jwtUtil.extractUsername(token);
            String role = jwtUtil.extractRole(token);

            // Crée une authentification pour cet utilisateur
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    jwtUtil.getAuthorities(role) // Récupère les permissions basées sur le rôle
            );

            // Définit l'authentification dans le contexte de sécurité
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        // Passe au filtre suivant dans la chaîne
        filterChain.doFilter(request, response);
    }
}