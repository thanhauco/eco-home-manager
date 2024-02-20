package com.ecohome.repository;

import com.ecohome.model.Device;
import com.ecohome.model.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findByRoomId(Long roomId);

    List<Device> findByType(DeviceType type);

    long countByStatusTrue();
}
