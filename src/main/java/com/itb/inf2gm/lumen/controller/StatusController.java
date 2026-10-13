package com.itb.inf2gm.lumen.controller;

import com.itb.inf2gm.lumen.model.entity.Status;
import com.itb.inf2gm.lumen.service.StatusService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/status")
public class StatusController {

    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    public List<Status> findAll() {
        return statusService.findAll();
    }

    @GetMapping("/{id}")
    public Status findById(@PathVariable Long id) {
        return statusService.findById(id);
    }

    @PostMapping
    public Status save(@RequestBody Status status) {
        return statusService.save(status);
    }

    @PutMapping("/{id}")
    public Status update(
            @PathVariable Long id,
            @RequestBody Status status) {

        return statusService.update(id, status);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        statusService.delete(id);
    }
}