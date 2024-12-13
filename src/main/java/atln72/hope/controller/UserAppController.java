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

import atln72.hope.model.UserAppEntity;
import atln72.hope.service.UserAppService;

@RestController
@RequestMapping("/users")
public class UserAppController {
    @Autowired
    private UserAppService userService;

    public UserAppController(UserAppService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserAppEntity>> fetchAllUsers(){
        List<UserAppEntity> users = userService.getAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserAppEntity> fetchUser(int userId){
        Optional<UserAppEntity> user = userService.getById(userId);

        return user.map(value -> ResponseEntity
            .status(HttpStatus.OK)
            .body(value))
            .orElseGet(() -> ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(null));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<String> createUserApp(@RequestBody UserAppEntity entity, @PathVariable int toolId) {
        try{
            if (entity.getUserId() != toolId)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User ID mismatch");

            userService.create(entity);
            
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

    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUserApp(@RequestBody UserAppEntity entity, @PathVariable int toolId) {
        try{
            if (entity.getUserId() != toolId)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User ID mismatch");

            userService.update(entity);
            
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
    
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserApp(@PathVariable int toolId) {
        try{
            userService.delete(toolId);
            
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
