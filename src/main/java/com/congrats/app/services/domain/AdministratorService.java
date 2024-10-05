package com.congrats.app.services.domain;

import com.congrats.app.models.entities.AdministratorEntity;
import com.congrats.app.repositories.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministratorService {
    @Autowired
    private AdministratorRepository administratorRepository;

    public List<AdministratorEntity> getAllAdministrators() {
        return administratorRepository.findAll();
    }

    public Optional<AdministratorEntity> getAdministratorById(Long id) {
        return administratorRepository.findById(id);
    }

    public AdministratorEntity saveAdministrator(AdministratorEntity administrator) {
        return administratorRepository.save(administrator);
    }

    public void deleteAdministrator(Long id) {
        administratorRepository.deleteById(id);
    }

    public AdministratorEntity updateAdministrator(Long id, AdministratorEntity administratorDetails) {
        return administratorRepository.findById(id).map(administrator -> {
            administrator.setName(administratorDetails.getName());
            administrator.setEmail(administratorDetails.getEmail());
            administrator.setCellphone(administratorDetails.getCellphone());
            administrator.setPassword(administratorDetails.getPassword());
            return administratorRepository.save(administrator);
        }).orElseGet(() -> {
            administratorDetails.setId(id);
            return administratorRepository.save(administratorDetails);
        });
    }
}