package atln72.hope.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/toolproposition")
public class ToolPropositionController {
    @GetMapping
    public String test(){
        return "tool proposition api";
    }
}
