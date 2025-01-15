package atln72.hope.service;

import atln72.hope.model.UserAppEntity;
import atln72.hope.repository.UserAppRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserAppService {
    private static final Logger logger = Logger.getLogger(UserAppService.class.getName());

    @Autowired
    private final UserAppRepository userAppRepository;

    public UserAppService(UserAppRepository userRepository) {
        this.userAppRepository = userRepository;
    }

    public List<UserAppEntity> getAll() {
        logger.log(Level.INFO, "Fetching all Users");
        return userAppRepository.findAll();
    }

    public Optional<UserAppEntity> getById(int id) {
        logger.log(Level.INFO, "Fetching User by id: {0}", id);
        return userAppRepository.findById(id);
    }

    public void create(UserAppEntity userAppEntity) {
        if (parameterMissing(userAppEntity)) {
            logger.log(Level.SEVERE, "Parameters missing while creating User");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parameters missing");
        }
        Optional<UserAppEntity> userAppEntityOptional = this.getById(userAppEntity.getUserId());
        if (userAppEntityOptional.isPresent()) {
            logger.log(Level.WARNING, "User already exists with ID: {0}", userAppEntity.getUserId());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
        }
        userAppRepository.save(userAppEntity);
        logger.log(Level.INFO, "User created with ID: {0}", userAppEntity.getUserId());
    }

    public void update(UserAppEntity userAppBody) {
        if (parameterMissing(userAppBody)) {
            logger.log(Level.SEVERE, "Parameters missing while updating User");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parameters missing");
        }
        Optional<UserAppEntity> userAppEntityOptional = this.getById(userAppBody.getUserId());
        if (userAppEntityOptional.isEmpty()) {
            logger.log(Level.WARNING, "User not found with ID: {0}", userAppBody.getUserId());
            throw new IllegalArgumentException("User not found");
        }
        UserAppEntity userAppEntity = userAppEntityOptional.get();
        BeanUtils.copyProperties(userAppBody, userAppEntity);
        userAppRepository.save(userAppEntity);
        logger.log(Level.INFO, "User updated with ID: {0}", userAppEntity.getUserId());
    }

    public void delete(Integer id) {
        if (this.getById(id) == null) {
            logger.log(Level.WARNING, "User not found with ID: {0}", id);
            throw new IllegalArgumentException("User not found");
        }
        userAppRepository.deleteById(id);
        logger.log(Level.INFO, "User deleted with ID: {0}", id);
    }

    private boolean parameterMissing(UserAppEntity userAppEntity) {
        return userAppEntity.getEmail() == null || userAppEntity.getPassword() == null || userAppEntity.getUserRole() == null || userAppEntity.getUsername() == null;
    }

    public UserAppEntity authenticate(String username, String password) {
        logger.log(Level.INFO, "Authenticating User: {0}", username);
        List<UserAppEntity> users = this.getAll();
        users.removeIf(user -> !user.getUsername().equals(username) || !user.getPassword().equals(password));
        if (users.isEmpty()) {
            logger.log(Level.WARNING, "User not found with username: {0}", username);
            return null;
        }
        else {
            logger.log(Level.INFO, "User authenticated: {0}", username);
            return users.get(0);
        }
    }
}