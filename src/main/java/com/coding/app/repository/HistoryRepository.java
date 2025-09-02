package com.coding.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coding.app.models.History;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long>{

}
