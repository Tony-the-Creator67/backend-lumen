package com.itb.inf2gm.lumen.service;

import com.itb.inf2gm.lumen.model.entity.Usuario;
import com.itb.inf2gm.lumen.model.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public Usuario save(Usuario usuario) {

        if (usuario.getDataCadastro() == null) {
            usuario.setDataCadastro(LocalDateTime.now());
        }

        if (usuario.getAtivo() == null) {
            usuario.setAtivo(true);
        }

        return usuarioRepository.save(usuario);
    }

    public Usuario update(Long id, Usuario usuarioAtualizado) {

        Usuario usuarioExistente = findById(id);

        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setCpf(usuarioAtualizado.getCpf());
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());
        usuarioExistente.setSenhaHash(usuarioAtualizado.getSenhaHash());
        usuarioExistente.setTelefone(usuarioAtualizado.getTelefone());
        usuarioExistente.setPerfil(usuarioAtualizado.getPerfil());
        usuarioExistente.setAtivo(usuarioAtualizado.getAtivo());

        return usuarioRepository.save(usuarioExistente);
    }

    public Usuario login(String email, String senhaHash) {

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("E-mail ou senha inválidos"));

        if (!usuario.getSenhaHash().equals(senhaHash)) {
            throw new RuntimeException("E-mail ou senha inválidos");
        }

        if (Boolean.FALSE.equals(usuario.getAtivo())) {
            throw new RuntimeException("Usuário inativo");
        }

        return usuario;
    }

    public void delete(Long id) {

        Usuario usuarioExistente = findById(id);

        usuarioRepository.delete(usuarioExistente);
    }
}