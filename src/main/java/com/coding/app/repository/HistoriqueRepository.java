package com.coding.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coding.app.models.Historique;

@Repository
public interface HistoriqueRepository extends JpaRepository<Historique, Long>{

}
