package com.itb.inf2gm.lumen.service;

import com.itb.inf2gm.lumen.model.entity.Denuncia;
import com.itb.inf2gm.lumen.model.entity.HistoricoStatus;
import com.itb.inf2gm.lumen.model.entity.Status;
import com.itb.inf2gm.lumen.model.entity.Usuario;
import com.itb.inf2gm.lumen.model.repository.DenunciaRepository;
import com.itb.inf2gm.lumen.model.repository.HistoricoStatusRepository;
import com.itb.inf2gm.lumen.model.repository.StatusRepository;
import com.itb.inf2gm.lumen.model.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoricoStatusService {

    private final HistoricoStatusRepository historicoStatusRepository;
    private final DenunciaRepository denunciaRepository;
    private final StatusRepository statusRepository;
    private final UsuarioRepository usuarioRepository;

    public HistoricoStatusService(
            HistoricoStatusRepository historicoStatusRepository,
            DenunciaRepository denunciaRepository,
            StatusRepository statusRepository,
            UsuarioRepository usuarioRepository) {

        this.historicoStatusRepository = historicoStatusRepository;
        this.denunciaRepository = denunciaRepository;
        this.statusRepository = statusRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<HistoricoStatus> findAll() {
        return historicoStatusRepository.findAll();
    }

    public HistoricoStatus findById(Long id) {
        return historicoStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Histórico não encontrado"));
    }

    public HistoricoStatus save(HistoricoStatus historicoStatus) {

        if (historicoStatus.getDataAlteracao() == null) {
            historicoStatus.setDataAlteracao(LocalDateTime.now());
        }

        Denuncia denuncia = denunciaRepository.findById(
                        historicoStatus.getDenuncia().getIdDenuncia())
                .orElseThrow(() -> new RuntimeException("Denúncia não encontrada"));

        Status status = statusRepository.findById(
                        historicoStatus.getStatus().getIdStatus())
                .orElseThrow(() -> new RuntimeException("Status não encontrado"));

        historicoStatus.setDenuncia(denuncia);
        historicoStatus.setStatus(status);

        if (historicoStatus.getUsuario() != null &&
                historicoStatus.getUsuario().getIdUsuario() != null) {

            Usuario usuario = usuarioRepository.findById(
                            historicoStatus.getUsuario().getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            historicoStatus.setUsuario(usuario);
        }

        return historicoStatusRepository.save(historicoStatus);
    }

    public void delete(Long id) {
        HistoricoStatus historico = findById(id);
        historicoStatusRepository.delete(historico);
    }
}