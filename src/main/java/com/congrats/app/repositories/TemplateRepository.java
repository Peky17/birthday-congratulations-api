package com.congrats.app.repositories;

import com.congrats.app.models.entities.TemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemplateRepository  extends JpaRepository<TemplateEntity, Long>{
    List<TemplateEntity> findTopByOrderByIdDesc();
}
