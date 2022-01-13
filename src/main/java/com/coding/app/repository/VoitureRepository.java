package com.coding.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coding.app.models.Voiture;

@Repository
public interface VoitureRepository extends JpaRepository<Voiture, Long>{

}
