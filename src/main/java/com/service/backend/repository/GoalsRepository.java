package com.service.backend.repository;

import com.service.backend.repository.entities.GoalsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Severiano Atencio
 */
@Repository
public interface GoalsRepository extends JpaRepository<GoalsEntity, Long> {

    @Query(value = "SELECT * FROM goals WHERE id=?1", nativeQuery = true)
    GoalsEntity fetchUserGoals(String clientId);

}
