package atln72.hope.service;

import atln72.hope.model.UserAppEntity;
import atln72.hope.repository.UserAppRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAppService {
    @Autowired
    private final UserAppRepository userAppRepository;

    public UserAppService(UserAppRepository userRepository) {
        this.userAppRepository = userRepository;
    }

    public List<UserAppEntity> getAll() {
        return userAppRepository.findAll();
    }

    public Optional<UserAppEntity> getById(int id) {
        return userAppRepository.findById(id);
    }

    public void create(UserAppEntity userAppEntity) {
        if (parameterMissing(userAppEntity))
            throw new IllegalArgumentException("Parameters missing");

        Optional<UserAppEntity> userAppEntityOptional = this.getById(userAppEntity.getUserId());
        if (userAppEntityOptional.isPresent())
            throw new IllegalArgumentException("User already exists");

        userAppRepository.save(userAppEntity);
    }

    public void update(UserAppEntity userAppBody) {
        if (parameterMissing(userAppBody))
            throw new IllegalArgumentException("Parameters missing");

        Optional<UserAppEntity> userAppEntityOptional = this.getById(userAppBody.getUserId());
        if (userAppEntityOptional.isEmpty())
            throw new IllegalArgumentException("User not found");
        
        UserAppEntity userAppEntity = userAppEntityOptional.get();
        BeanUtils.copyProperties(userAppBody, userAppEntity);
        userAppRepository.save(userAppEntity);
    }

    public void delete(Integer id) {
        if (this.getById(id) == null)
            throw new IllegalArgumentException("User not found");

        userAppRepository.deleteById(id);
    }

    private boolean parameterMissing(UserAppEntity userAppEntity) {
        return userAppEntity.getEmail() == null || userAppEntity.getPassword() == null || userAppEntity.getUserRole() == null || userAppEntity.getUsername() == null;
    }
}