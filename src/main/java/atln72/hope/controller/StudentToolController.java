package atln72.hope.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import atln72.hope.model.StudentToolEntity;
import atln72.hope.service.StudentToolService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/studenttool")
public class StudentToolController {
    @Autowired
    private StudentToolService studentToolService;

    public StudentToolController(StudentToolService studentToolService) {
        this.studentToolService = studentToolService;
    }   

    @GetMapping
    public ResponseEntity<List<StudentToolEntity>> fetchAllStudentTools() {
        List<StudentToolEntity> studentTools = studentToolService.getAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentTools);
    }

    @GetMapping("/{toolId}")
    public ResponseEntity<StudentToolEntity> fetchStudentTool(@PathVariable int toolId) {
        Optional<StudentToolEntity> studentTool = studentToolService.getById(toolId);

        return studentTool.map(value -> ResponseEntity
            .status(HttpStatus.OK)
            .body(value))
            .orElseGet(() -> ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(null));
    }

    @PostMapping("/{toolId}")
    public ResponseEntity<String> createStudentTool(@RequestBody StudentToolEntity entity, @PathVariable int toolId) {
        try{
            if (entity.getToolId() != toolId)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tool ID mismatch");

            studentToolService.create(entity);
            
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

    @PutMapping("/{toolId}")
    public ResponseEntity<String> updateStudentTool(@RequestBody StudentToolEntity entity, @PathVariable int toolId) {
        try{
            if (entity.getToolId() != toolId)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tool ID mismatch");

            studentToolService.update(entity);
            
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
    
    @DeleteMapping("/{toolId}")
    public ResponseEntity<String> deleteStudentTool(@PathVariable int toolId) {
        try{
            studentToolService.delete(toolId);
            
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
