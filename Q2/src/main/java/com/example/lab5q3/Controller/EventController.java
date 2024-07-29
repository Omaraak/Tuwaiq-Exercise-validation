package com.example.lab5q3.Controller;

import com.example.lab5q3.ApiResponse;
import com.example.lab5q3.Model.Event;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1")
public class EventController {
    ArrayList<Event> events = new ArrayList<Event>();

    @PostMapping("/add")
    public ResponseEntity addEvent(@Valid @RequestBody Event event, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(errorMessage);
        }
        events.add(event);
        return ResponseEntity.status(200).body(new ApiResponse("event added"));
    }

    @GetMapping("/displayAll")
    public ResponseEntity displayAllEvents() {
        return ResponseEntity.status(200).body(events);
    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateEvent(@PathVariable int index,@Valid @RequestBody Event event, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(errorMessage);
        }
        if (index < events.size()) {
            events.set(index, event);
            return ResponseEntity.status(200).body(new ApiResponse("event updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("event not found"));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEvent(@PathVariable int index) {
        if (index < events.size()) {
            events.remove(index);
            return ResponseEntity.status(200).body(new ApiResponse("event deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("event not found"));
    }

    @PutMapping("/changeCapacity/{index}/{capacity}")
    public ResponseEntity changeCapacity(@PathVariable int index, @PathVariable int capacity) {
        if (index < events.size()) {
            events.get(index).setCapacity(capacity);
            return ResponseEntity.status(200).body("event updated");
        }
        return ResponseEntity.status(400).body("event not found");
    }

    @GetMapping("/eventById/{id}")
    public ResponseEntity getEventById(@PathVariable int id) {
        for (Event event : events) {
            if (event.getId() == id) {
                return ResponseEntity.status(200).body(event);
            }
        }
        return ResponseEntity.status(404).body("event not found");
    }
}
