package com.itb.inf2gm.lumen.controller;

import com.itb.inf2gm.lumen.model.entity.Imagem;
import com.itb.inf2gm.lumen.service.ImagemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/imagem")
public class ImagemController {

    private final ImagemService imagemService;

    public ImagemController(ImagemService imagemService) {
        this.imagemService = imagemService;
    }

    @GetMapping
    public List<Imagem> findAll() {
        return imagemService.findAll();
    }

    @GetMapping("/{id}")
    public Imagem findById(@PathVariable Long id) {
        return imagemService.findById(id);
    }

    @PostMapping
    public Imagem save(@RequestBody Imagem imagem) {
        return imagemService.save(imagem);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        imagemService.delete(id);
    }
}