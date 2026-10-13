package com.itb.inf2gm.lumen.service;

import com.itb.inf2gm.lumen.model.entity.Status;
import com.itb.inf2gm.lumen.model.repository.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {

    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<Status> findAll() {
        return statusRepository.findAll();
    }

    public Status findById(Long id) {
        return statusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Status não encontrado"));
    }

    public Status save(Status status) {
        return statusRepository.save(status);
    }

    public Status update(Long id, Status statusAtualizado) {

        Status statusExistente = findById(id);

        statusExistente.setCodigoStatus(statusAtualizado.getCodigoStatus());
        statusExistente.setNomeStatus(statusAtualizado.getNomeStatus());

        return statusRepository.save(statusExistente);
    }

    public void delete(Long id) {

        Status statusExistente = findById(id);

        statusRepository.delete(statusExistente);
    }
}