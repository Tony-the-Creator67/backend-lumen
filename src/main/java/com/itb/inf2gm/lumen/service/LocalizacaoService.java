package com.itb.inf2gm.lumen.service;

import com.itb.inf2gm.lumen.model.entity.Localizacao;
import com.itb.inf2gm.lumen.model.repository.LocalizacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalizacaoService {

    private final LocalizacaoRepository localizacaoRepository;

    public LocalizacaoService(LocalizacaoRepository localizacaoRepository) {
        this.localizacaoRepository = localizacaoRepository;
    }

    public List<Localizacao> findAll() {
        return localizacaoRepository.findAll();
    }

    public Localizacao findById(Long id) {
        return localizacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Localização não encontrada"));
    }

    public Localizacao save(Localizacao localizacao) {
        return localizacaoRepository.save(localizacao);
    }

    public Localizacao update(Long id, Localizacao localizacaoAtualizada) {

        Localizacao localizacaoExistente = findById(id);

        localizacaoExistente.setLogradouro(localizacaoAtualizada.getLogradouro());
        localizacaoExistente.setBairro(localizacaoAtualizada.getBairro());
        localizacaoExistente.setCep(localizacaoAtualizada.getCep());
        localizacaoExistente.setLatitude(localizacaoAtualizada.getLatitude());
        localizacaoExistente.setLongitude(localizacaoAtualizada.getLongitude());

        return localizacaoRepository.save(localizacaoExistente);
    }

    public void delete(Long id) {

        Localizacao localizacaoExistente = findById(id);

        localizacaoRepository.delete(localizacaoExistente);
    }
}