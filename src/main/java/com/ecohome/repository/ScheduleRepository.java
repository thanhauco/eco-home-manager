package com.ecohome.repository;

import com.ecohome.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByDeviceId(Long deviceId);

    List<Schedule> findByActiveTrue();
}
