package com.residex.residence.repository;

import com.residex.residence.entity.Residence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidenceRepository
        extends JpaRepository<Residence, Long> {
}