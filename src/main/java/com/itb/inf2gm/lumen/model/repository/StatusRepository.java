package com.itb.inf2gm.lumen.model.repository;

import com.itb.inf2gm.lumen.model.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

}