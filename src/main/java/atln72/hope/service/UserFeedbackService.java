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
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserFeedbackService {
    private static final Logger logger = Logger.getLogger(UserFeedbackService.class.getName());

    @Autowired
    private final UserFeedbackRepository userFeedbackRepository;

    public UserFeedbackService(UserFeedbackRepository repository) {
        this.userFeedbackRepository = repository;
    }

    public List<UserFeedbackEntity> getAll() {
        logger.log(Level.INFO, "Fetching all Feedbacks");
        return userFeedbackRepository.findAll();
    }

    public Optional<UserFeedbackEntity> getById(int id) {
        logger.log(Level.INFO, "Fetching Feedback by id: {0}", id);
        return userFeedbackRepository.findById(id);
    }

    public List<UserFeedbackEntity> getByTool(int toolId) {
        logger.log(Level.INFO, "Fetching Feedbacks by Tool ID: {0}", toolId);
        List <UserFeedbackEntity> feedbacks = this.getAll();
        feedbacks.removeIf(feedback -> feedback.getStudentTool().getToolId() != toolId);
        return feedbacks;
    }

    public void create(UserFeedbackEntity feedback) {
        if (parameterMissing(feedback)) {
            logger.log(Level.SEVERE, "Parameters missing while creating Feedback");
            logger.log(Level.INFO, "content: {0}", feedback.getContent());
            logger.log(Level.INFO, "tool: {0}", feedback.getStudentTool());
            logger.log(Level.INFO, "user: {0}", feedback.getUser());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parameters missing");
        }
        userFeedbackRepository.save(feedback);
        logger.log(Level.INFO, "Feedback created with ID: {0}", feedback.getUserFeedbackId());
    }

    public void update(UserFeedbackEntity feedbackBody) {
        if (parameterMissing(feedbackBody)) {
            logger.log(Level.SEVERE, "Parameters missing while updating Feedback");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parameters missing");
        }
        Optional<UserFeedbackEntity> existingFeedback = this.getById(feedbackBody.getUserFeedbackId());
        if (existingFeedback.isEmpty()) {
            logger.log(Level.WARNING, "Feedback not found with ID: {0}", feedbackBody.getUserFeedbackId());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Feedback not found");
        }
        UserFeedbackEntity existingEntity = existingFeedback.get();
        BeanUtils.copyProperties(feedbackBody, existingEntity, "userFeedbackId");
        userFeedbackRepository.save(existingEntity);
        logger.log(Level.INFO, "Feedback updated with ID: {0}", feedbackBody.getUserFeedbackId());
    }

    public void delete(int id) {
        if (this.getById(id).isEmpty()) {
            logger.log(Level.WARNING, "Feedback not found with ID: {0}", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Feedback not found");
        }
        userFeedbackRepository.deleteById(id);
        logger.log(Level.INFO, "Feedback deleted with ID: {0}", id);
    }

    private boolean parameterMissing(UserFeedbackEntity feedback) {
        return feedback.getContent() == null || feedback.getContent().isEmpty() || feedback.getUser() == null || feedback.getStudentTool() == null;
    }
}
