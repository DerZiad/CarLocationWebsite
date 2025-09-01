package com.coding.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coding.app.models.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{

}
