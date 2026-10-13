package com.itb.inf2gm.lumen.model.repository;

import com.itb.inf2gm.lumen.model.entity.Localizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalizacaoRepository extends JpaRepository<Localizacao, Long> {

}