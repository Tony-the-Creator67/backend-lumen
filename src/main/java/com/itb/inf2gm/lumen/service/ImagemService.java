package com.itb.inf2gm.lumen.service;

import com.itb.inf2gm.lumen.model.entity.Denuncia;
import com.itb.inf2gm.lumen.model.entity.Imagem;
import com.itb.inf2gm.lumen.model.repository.DenunciaRepository;
import com.itb.inf2gm.lumen.model.repository.ImagemRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ImagemService {

    private final ImagemRepository imagemRepository;
    private final DenunciaRepository denunciaRepository;

    public ImagemService(
            ImagemRepository imagemRepository,
            DenunciaRepository denunciaRepository) {

        this.imagemRepository = imagemRepository;
        this.denunciaRepository = denunciaRepository;
    }

    public List<Imagem> findAll() {
        return imagemRepository.findAll();
    }

    public Imagem findById(Long id) {
        return imagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imagem não encontrada"));
    }

    public Imagem save(Imagem imagem) {

        if (imagem.getDataUpload() == null) {
            imagem.setDataUpload(LocalDateTime.now());
        }

        Denuncia denuncia = denunciaRepository.findById(
                        imagem.getDenuncia().getIdDenuncia())
                .orElseThrow(() -> new RuntimeException("Denúncia não encontrada"));

        imagem.setDenuncia(denuncia);

        return imagemRepository.save(imagem);
    }

    public void delete(Long id) {
        Imagem imagem = findById(id);
        imagemRepository.delete(imagem);
    }
}