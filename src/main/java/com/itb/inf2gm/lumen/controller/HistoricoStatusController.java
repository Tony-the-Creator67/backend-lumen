package com.itb.inf2gm.lumen.controller;

import com.itb.inf2gm.lumen.model.entity.HistoricoStatus;
import com.itb.inf2gm.lumen.service.HistoricoStatusService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/historico-status")
public class HistoricoStatusController {

    private final HistoricoStatusService historicoStatusService;

    public HistoricoStatusController(HistoricoStatusService historicoStatusService) {
        this.historicoStatusService = historicoStatusService;
    }

    @GetMapping
    public List<HistoricoStatus> findAll() {
        return historicoStatusService.findAll();
    }

    @GetMapping("/{id}")
    public HistoricoStatus findById(@PathVariable Long id) {
        return historicoStatusService.findById(id);
    }

    @PostMapping
    public HistoricoStatus save(@RequestBody HistoricoStatus historicoStatus) {
        return historicoStatusService.save(historicoStatus);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        historicoStatusService.delete(id);
    }
}