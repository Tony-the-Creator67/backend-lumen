package com.itb.inf2gm.lumen.service;

import com.itb.inf2gm.lumen.model.entity.Categoria;
import com.itb.inf2gm.lumen.model.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria findById(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }

    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria update(Long id, Categoria categoriaAtualizada) {

        Categoria categoriaExistente = findById(id);

        categoriaExistente.setNome(categoriaAtualizada.getNome());
        categoriaExistente.setDescricao(categoriaAtualizada.getDescricao());
        categoriaExistente.setAtivo(categoriaAtualizada.getAtivo());

        return categoriaRepository.save(categoriaExistente);
    }

    public void delete(Long id) {

        Categoria categoriaExistente = findById(id);

        categoriaRepository.delete(categoriaExistente);
    }
}