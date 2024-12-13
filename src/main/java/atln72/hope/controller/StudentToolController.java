package atln72.hope.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/studenttool")
public class StudentToolController {
    @GetMapping
    public String test(){
        return "student tool api";
    }
}
