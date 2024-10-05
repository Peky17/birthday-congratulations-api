package com.congrats.app.repositories;

import com.congrats.app.models.entities.LogActionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogActionRepository  extends JpaRepository<LogActionEntity, Long> { }
