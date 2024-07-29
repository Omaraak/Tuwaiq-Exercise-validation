package com.example.lab5q2.Controller;

import com.example.lab5q2.Model.Project;
import com.example.lab5q2.Response.Response;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1")
public class ProjectController {
    ArrayList<Project> projects = new ArrayList<>();

    @PostMapping("/add")
    public ResponseEntity addProject(@Valid @RequestBody Project project, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(errorMessage);
        }
        projects.add(project);
        return ResponseEntity.status(200).body(new Response("Successfully added project"));
    }

    @GetMapping("/displayAll")
    public ResponseEntity displayAllProjects() {
        return ResponseEntity.status(200).body(projects);
    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateProject(@PathVariable int index, @Valid@RequestBody Project project, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(errorMessage);
        }
        if (index < projects.size()) {
            projects.set(index, project);
            return ResponseEntity.status(200).body(new Response("Project updated"));
        }
        return ResponseEntity.status(400).body(new Response("Project not found"));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteProject(@PathVariable int index) {
        if (index < projects.size()) {
            projects.remove(index);
            return ResponseEntity.status(200).body(new Response("Project deleted"));
        }
        return ResponseEntity.status(400).body("Project not found");
    }

    @PutMapping("/changeProjectStatus/{id}")
    public ResponseEntity changeProjectStatus(@PathVariable int id) {
        for (Project project : projects) {
            if (project.getId() == id) {
                if (project.getStatus().equals("Not Started")) {
                    project.setStatus("In Progress");
                    return ResponseEntity.status(200).body(new Response("Project status changed"));
                }
                else if (project.getStatus().equals("In Progress")) {
                    project.setStatus("Completed");
                    return ResponseEntity.status(200).body(new Response("Project status changed"));
                }
                else
                    return ResponseEntity.status(200).body("Project status is done");
            }
        }
        return ResponseEntity.status(400).body("Project not found");
    }

    @GetMapping("/projectByTitle/{title}")
    public ResponseEntity getProjectByTitle(@PathVariable String title) {
        for (Project project : projects) {
            if (project.getTitle().equals(title)) {
                return ResponseEntity.status(200).body(project);
            }
        }
        return ResponseEntity.status(400).body("Project not found");
    }

    @GetMapping("/projectsByCompanyName/{companyName}")
    public ResponseEntity getProjectsByCompanyName(@PathVariable String companyName) {
        ArrayList<Project> companyProjects = new ArrayList<>();
        for (Project project : projects) {
            if (project.getCompanyName().equals(companyName)) {
                companyProjects.add(project);
            }
        }
        return ResponseEntity.status(200).body(companyProjects);
    }
}
