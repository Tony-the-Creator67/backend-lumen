package com.itb.inf2gm.lumen.controller;

import com.itb.inf2gm.lumen.model.entity.Denuncia;
import com.itb.inf2gm.lumen.service.DenunciaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/denuncia")
public class DenunciaController {

    private final DenunciaService denunciaService;

    public DenunciaController(DenunciaService denunciaService) {
        this.denunciaService = denunciaService;
    }

    @GetMapping
    public List<Denuncia> findAll() {
        return denunciaService.findAll();
    }

    @GetMapping("/{id}")
    public Denuncia findById(@PathVariable Long id) {
        return denunciaService.findById(id);
    }

    @PostMapping
    public Denuncia save(@RequestBody Denuncia denuncia) {
        return denunciaService.save(denuncia);
    }

    @PutMapping("/{id}")
    public Denuncia update(
            @PathVariable Long id,
            @RequestBody Denuncia denuncia) {

        return denunciaService.update(id, denuncia);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        denunciaService.delete(id);
    }
}