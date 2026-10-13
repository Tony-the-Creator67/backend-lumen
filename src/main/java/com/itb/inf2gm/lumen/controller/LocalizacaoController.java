package com.itb.inf2gm.lumen.controller;

import com.itb.inf2gm.lumen.model.entity.Localizacao;
import com.itb.inf2gm.lumen.service.LocalizacaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/localizacao")
public class LocalizacaoController {

    private final LocalizacaoService localizacaoService;

    public LocalizacaoController(LocalizacaoService localizacaoService) {
        this.localizacaoService = localizacaoService;
    }

    @GetMapping
    public List<Localizacao> findAll() {
        return localizacaoService.findAll();
    }

    @GetMapping("/{id}")
    public Localizacao findById(@PathVariable Long id) {
        return localizacaoService.findById(id);
    }

    @PostMapping
    public Localizacao save(@RequestBody Localizacao localizacao) {
        return localizacaoService.save(localizacao);
    }

    @PutMapping("/{id}")
    public Localizacao update(
            @PathVariable Long id,
            @RequestBody Localizacao localizacao) {

        return localizacaoService.update(id, localizacao);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        localizacaoService.delete(id);
    }
}