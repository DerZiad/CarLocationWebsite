package com.coding.app.repository;

import com.coding.app.models.EmailVerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationRepository extends JpaRepository<EmailVerificationCode, Long> {

}
