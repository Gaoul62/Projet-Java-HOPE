package atln72.hope.service;

import atln72.hope.model.UserAppEntity;
import atln72.hope.repository.UserAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAppService {
    @Autowired
    private final UserAppRepository userAppRepository;

    public UserAppService(UserAppRepository userRepository) {
        this.userAppRepository = userRepository;
    }

    public List<UserAppEntity> getAllUsers() {
        return userAppRepository.findAll();
    }
}