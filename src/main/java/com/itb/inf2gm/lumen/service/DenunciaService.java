package com.itb.inf2gm.lumen.service;

import com.itb.inf2gm.lumen.model.entity.Categoria;
import com.itb.inf2gm.lumen.model.entity.Denuncia;
import com.itb.inf2gm.lumen.model.entity.Localizacao;
import com.itb.inf2gm.lumen.model.entity.Status;
import com.itb.inf2gm.lumen.model.entity.Usuario;
import com.itb.inf2gm.lumen.model.repository.CategoriaRepository;
import com.itb.inf2gm.lumen.model.repository.DenunciaRepository;
import com.itb.inf2gm.lumen.model.repository.LocalizacaoRepository;
import com.itb.inf2gm.lumen.model.repository.StatusRepository;
import com.itb.inf2gm.lumen.model.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DenunciaService {

    private final DenunciaRepository denunciaRepository;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;
    private final LocalizacaoRepository localizacaoRepository;
    private final StatusRepository statusRepository;

    public DenunciaService(
            DenunciaRepository denunciaRepository,
            UsuarioRepository usuarioRepository,
            CategoriaRepository categoriaRepository,
            LocalizacaoRepository localizacaoRepository,
            StatusRepository statusRepository) {

        this.denunciaRepository = denunciaRepository;
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
        this.localizacaoRepository = localizacaoRepository;
        this.statusRepository = statusRepository;
    }

    public List<Denuncia> findAll() {
        return denunciaRepository.findAll();
    }

    public Denuncia findById(Long id) {
        return denunciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Denúncia não encontrada"));
    }

    public Denuncia save(Denuncia denuncia) {

        if (denuncia.getDataRegistro() == null) {
            denuncia.setDataRegistro(LocalDateTime.now());
        }

        if (denuncia.getAnonima() == null) {
            denuncia.setAnonima(false);
        }

        if (denuncia.getUsuario() != null && denuncia.getUsuario().getIdUsuario() != null) {
            Usuario usuario = usuarioRepository.findById(denuncia.getUsuario().getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            denuncia.setUsuario(usuario);
        }

        Categoria categoria = categoriaRepository.findById(
                        denuncia.getCategoria().getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Localizacao localizacao = localizacaoRepository.findById(
                        denuncia.getLocalizacao().getIdLocalizacao())
                .orElseThrow(() -> new RuntimeException("Localização não encontrada"));

        Status status = statusRepository.findById(
                        denuncia.getStatusAtual().getIdStatus())
                .orElseThrow(() -> new RuntimeException("Status não encontrado"));

        denuncia.setCategoria(categoria);
        denuncia.setLocalizacao(localizacao);
        denuncia.setStatusAtual(status);

        return denunciaRepository.save(denuncia);
    }

    public Denuncia update(Long id, Denuncia denunciaAtualizada) {

        Denuncia denunciaExistente = findById(id);

        denunciaExistente.setTitulo(denunciaAtualizada.getTitulo());
        denunciaExistente.setDescricao(denunciaAtualizada.getDescricao());
        denunciaExistente.setAnonima(denunciaAtualizada.getAnonima());

        if (denunciaAtualizada.getUsuario() != null &&
                denunciaAtualizada.getUsuario().getIdUsuario() != null) {

            Usuario usuario = usuarioRepository.findById(
                            denunciaAtualizada.getUsuario().getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            denunciaExistente.setUsuario(usuario);
        }

        Categoria categoria = categoriaRepository.findById(
                        denunciaAtualizada.getCategoria().getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Localizacao localizacao = localizacaoRepository.findById(
                        denunciaAtualizada.getLocalizacao().getIdLocalizacao())
                .orElseThrow(() -> new RuntimeException("Localização não encontrada"));

        Status status = statusRepository.findById(
                        denunciaAtualizada.getStatusAtual().getIdStatus())
                .orElseThrow(() -> new RuntimeException("Status não encontrado"));

        denunciaExistente.setCategoria(categoria);
        denunciaExistente.setLocalizacao(localizacao);
        denunciaExistente.setStatusAtual(status);

        return denunciaRepository.save(denunciaExistente);
    }

    public void delete(Long id) {
        Denuncia denuncia = findById(id);
        denunciaRepository.delete(denuncia);
    }
}