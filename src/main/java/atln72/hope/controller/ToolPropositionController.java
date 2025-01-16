package atln72.hope.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import atln72.hope.model.ToolPropositionEntity;
import atln72.hope.service.ToolPropositionService;
import atln72.hope.util.UserContext;

@Controller
@RequestMapping("/propositions")
public class ToolPropositionController {
    @Autowired
    private ToolPropositionService toolPropositionService;

    public ToolPropositionController(ToolPropositionService toolPropositionService) {
        this.toolPropositionService = toolPropositionService;
    }

    @GetMapping
    public ResponseEntity<List<ToolPropositionEntity>> fetchAllPropositions(){
        List<ToolPropositionEntity> propositions = toolPropositionService.getAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(propositions);
    }

    @GetMapping("/{propositionId}")
    public ResponseEntity<ToolPropositionEntity> fetchProposition(int propositionId){
        Optional<ToolPropositionEntity> proposition = toolPropositionService.getById(propositionId);

        return proposition.map(value -> ResponseEntity
            .status(HttpStatus.OK)
            .body(value))
            .orElseGet(() -> ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(null));
    }

    @PostMapping("/{propositionId}")
    public ResponseEntity<String> createToolProposition(@RequestBody ToolPropositionEntity entity, @PathVariable int toolId) {
        try{
            if (entity.getToolPropositionId() != toolId)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Proposition ID mismatch");

                toolPropositionService.create(entity);
            
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

    @PutMapping("/{propositionId}")
    public ResponseEntity<String> updateToolProposition(@RequestBody ToolPropositionEntity entity, @PathVariable int toolId) {
        try{
            if (entity.getToolPropositionId() != toolId)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Proposition ID mismatch");

            toolPropositionService.update(entity);
            
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
    
    @DeleteMapping("/{propositionId}")
    public ResponseEntity<String> deleteToolProposition(@PathVariable int toolId) {
        try{
            toolPropositionService.delete(toolId);
            
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

    @GetMapping("/show")
    public String showPropositions(Model model) {
        model.addAttribute("userRole", UserContext.currentUser.getUserRole());
        model.addAttribute("propositions", toolPropositionService.getAll());
        return "listPropositions";
    }

    @GetMapping("/prepareAdd")
    public String prepareAddProposition(Model model) {
        model.addAttribute("proposition", new ToolPropositionEntity());
        return "addToolProposition";
    }

    @PostMapping("/addPropositionTool")
    public String addPropositionTool(ToolPropositionEntity proposition) {
        toolPropositionService.create(proposition);
        return "redirect:/propositions/show";
    }

    @GetMapping("{propositionId}/accept")
    public String acceptProposition(@PathVariable int propositionId) {
        Optional<ToolPropositionEntity> proposition = toolPropositionService.getById(propositionId);
        if (proposition.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Proposition not found");
        }
        toolPropositionService.accept(proposition.get());
        return "redirect:/propositions/show";
    }

    @GetMapping("{propositionId}/refuse")
    public String refuseProposition(@PathVariable int propositionId) {
        Optional<ToolPropositionEntity> proposition = toolPropositionService.getById(propositionId);
        if (proposition.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Proposition not found");
        }
        toolPropositionService.refuse(proposition.get());
        return "redirect:/propositions/show";
    }
}
