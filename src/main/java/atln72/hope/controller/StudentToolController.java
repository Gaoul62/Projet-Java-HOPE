package atln72.hope.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import atln72.hope.model.StudentToolEntity;
import atln72.hope.service.StudentToolService;

@RestController
@RequestMapping("/studenttool")
public class StudentToolController {
    @Autowired
    private StudentToolService studentToolService;

    public StudentToolController(StudentToolService studentToolService) {
        this.studentToolService = studentToolService;
    }   

    @GetMapping
    public ResponseEntity<List<StudentToolEntity>> fetchAllStudentTools(){
        List<StudentToolEntity> studentTools = studentToolService.getAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentTools);
    }
}
