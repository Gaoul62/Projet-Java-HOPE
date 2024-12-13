package atln72.hope.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import atln72.hope.model.UserFeedbackEntity;
import atln72.hope.service.UserFeedbackService;

@RestController
@RequestMapping("/userfeedback")
public class UserFeedbackController {
    @Autowired
    private UserFeedbackService userFeedbackService;

    public UserFeedbackController(UserFeedbackService userFeedbackService) {
        this.userFeedbackService = userFeedbackService;
    }   

    @GetMapping
    public ResponseEntity<List<UserFeedbackEntity>> fetchAllUserFeedbacks(){
        List<UserFeedbackEntity> userFeedbacks = userFeedbackService.getAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userFeedbacks);
    }

    @GetMapping("/{feedbackId}")
    public ResponseEntity<UserFeedbackEntity> fetchUserFeedback(int feedbackId){
        Optional<UserFeedbackEntity> userFeedback = userFeedbackService.getById(feedbackId);

        return userFeedback.map(value -> ResponseEntity
            .status(HttpStatus.OK)
            .body(value))
            .orElseGet(() -> ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(null));
    }

    @PostMapping("/{feedbackId}")
    public ResponseEntity<String> createUserFeedback(@RequestBody UserFeedbackEntity entity, @PathVariable int toolId) {
        try{
            if (entity.getUserFeedbackId() != toolId)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Feedback ID mismatch");

            userFeedbackService.create(entity);
            
            return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(null);
        } catch (ResponseStatusException e) {
            System.out.println(e.getMessage());
            return ResponseEntity
                .status(e.getStatusCode())
                .body(e.getReason());
        }
    }

    @PutMapping("/{feedbackId}")
    public ResponseEntity<String> updateUserFeedback(@RequestBody UserFeedbackEntity entity, @PathVariable int toolId) {
        try{
            if (entity.getUserFeedbackId() != toolId)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tool ID mismatch");

            userFeedbackService.update(entity);
            
            return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
        } catch (ResponseStatusException e) {
            System.out.println(e.getMessage());
            return ResponseEntity
                .status(e.getStatusCode())
                .body(e.getReason());
        }
    }
    
    @DeleteMapping("/{feedbackId}")
    public ResponseEntity<String> deleteUserFeedback(@PathVariable int toolId) {
        try{
            userFeedbackService.delete(toolId);
            
            return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
        } catch (ResponseStatusException e) {
            System.out.println(e.getMessage());
            return ResponseEntity
                .status(e.getStatusCode())
                .body(e.getReason());
        }
    }
}
