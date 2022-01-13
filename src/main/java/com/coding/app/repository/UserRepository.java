package com.coding.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coding.app.models.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public List<User> findByUsername(String username);
}
