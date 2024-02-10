package com.ecohome.repository;

import com.ecohome.model.Alert;
import com.ecohome.model.AlertSeverity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findByResolvedFalse();

    List<Alert> findBySeverity(AlertSeverity severity);
}
