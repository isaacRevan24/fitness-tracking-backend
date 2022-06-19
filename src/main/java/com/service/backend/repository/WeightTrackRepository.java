package com.service.backend.repository;

import com.service.backend.repository.entities.WeightTrackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Severiano Atencio
 */
@Repository
public interface WeightTrackRepository extends JpaRepository<WeightTrackEntity, String> {
}
