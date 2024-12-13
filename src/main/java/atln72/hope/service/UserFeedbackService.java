package atln72.hope.service;

import atln72.hope.model.UserFeedbackEntity;
import atln72.hope.repository.UserFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFeedbackService {
    @Autowired
    private final UserFeedbackRepository userFeedbackRepository;

    public UserFeedbackService(UserFeedbackRepository userFeedbackRepository) {
        this.userFeedbackRepository = userFeedbackRepository;
    }

    public List<UserFeedbackEntity> getAllUsers() {
        return userFeedbackRepository.findAll();
    }
}