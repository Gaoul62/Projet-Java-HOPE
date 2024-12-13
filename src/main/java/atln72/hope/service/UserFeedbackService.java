package atln72.hope.service;

import atln72.hope.model.UserFeedbackEntity;
import atln72.hope.repository.UserFeedbackRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserFeedbackService {
    @Autowired
    private final UserFeedbackRepository userFeedbackRepository;

    public UserFeedbackService(UserFeedbackRepository repository) {
        this.userFeedbackRepository = repository;
    }

    public List<UserFeedbackEntity> getAll() {
        return userFeedbackRepository.findAll();
    }

    public Optional<UserFeedbackEntity> getById(int id) {
        return userFeedbackRepository.findById(id);
    }

    public void create(UserFeedbackEntity feedback) {
        if (parameterMissing(feedback)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parameters missing");
        }
        userFeedbackRepository.save(feedback);
    }

    public void update(UserFeedbackEntity feedbackBody) {
        if (parameterMissing(feedbackBody)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parameters missing");
        }

        Optional<UserFeedbackEntity> existingFeedback = this.getById(feedbackBody.getUserFeedbackId());
        if (existingFeedback.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Feedback not found");
        }

        UserFeedbackEntity existingEntity = existingFeedback.get();
        BeanUtils.copyProperties(feedbackBody, existingEntity, "userFeedbackId");
        userFeedbackRepository.save(existingEntity);
    }

    public void delete(int id) {
        if (this.getById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Feedback not found");
        }
        userFeedbackRepository.deleteById(id);
    }

    private boolean parameterMissing(UserFeedbackEntity feedback) {
        return feedback.getContent() == null || feedback.getContent().isEmpty()
                || feedback.getUser() == null || feedback.getStudentTool() == null;
    }
}
