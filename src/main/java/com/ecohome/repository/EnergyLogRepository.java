package com.ecohome.repository;

import com.ecohome.model.EnergyLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EnergyLogRepository extends JpaRepository<EnergyLog, Long> {
    List<EnergyLog> findByDeviceIdAndTimestampBetween(Long deviceId, LocalDateTime start, LocalDateTime end);

    @Query("SELECT SUM(e.energyConsumed) FROM EnergyLog e WHERE e.timestamp BETWEEN :start AND :end")
    Double getTotalEnergyConsumed(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
