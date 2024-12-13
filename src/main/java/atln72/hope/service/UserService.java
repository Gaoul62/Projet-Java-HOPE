package atln72.hope.service;

import atln72.hope.model.UserAppEntity;
import atln72.hope.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserAppEntity> getAllUsers() {
        return userRepository.findAll();
    }
}