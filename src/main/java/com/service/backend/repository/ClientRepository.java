package com.service.backend.repository;

import com.service.backend.repository.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Severiano Atencio
 */
@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, String> {
}
