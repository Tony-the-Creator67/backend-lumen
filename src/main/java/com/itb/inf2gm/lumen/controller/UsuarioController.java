package com.itb.inf2gm.lumen.controller;

import com.itb.inf2gm.lumen.model.entity.Usuario;
import com.itb.inf2gm.lumen.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> findAll() {
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public Usuario findById(@PathVariable Long id) {
        return usuarioService.findById(id);
    }

    @PostMapping
    public Usuario save(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    @PostMapping("/login")
    public Usuario login(@RequestBody Map<String, String> loginData) {

        String email = loginData.get("email");
        String senhaHash = loginData.get("senhaHash");

        return usuarioService.login(email, senhaHash);
    }

    @PutMapping("/{id}")
    public Usuario update(
            @PathVariable Long id,
            @RequestBody Usuario usuario) {

        return usuarioService.update(id, usuario);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        usuarioService.delete(id);
    }
}