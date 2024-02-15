package com.ecohome.service;

import com.ecohome.dto.EnergyStatsDTO;
import com.ecohome.model.Device;
import com.ecohome.model.EnergyLog;
import com.ecohome.repository.DeviceRepository;
import com.ecohome.repository.EnergyLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class EnergyService {

    @Autowired
    private EnergyLogRepository energyLogRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    public void logEnergyUsage(Long deviceId, double consumption) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new RuntimeException("Device not found with id: " + deviceId));

        EnergyLog log = new EnergyLog();
        log.setDevice(device);
        log.setConsumption(consumption);
        log.setTimestamp(LocalDateTime.now());
        energyLogRepository.save(log);
    }

    public EnergyStatsDTO getEnergyStatsByDevice(Long deviceId, LocalDateTime startDate, LocalDateTime endDate) {
        List<EnergyLog> logs = energyLogRepository.findByDeviceIdAndTimestampBetween(deviceId, startDate, endDate);

        double totalConsumption = logs.stream()
                .mapToDouble(EnergyLog::getConsumption)
                .sum();

        double averageConsumption = logs.isEmpty() ? 0 : totalConsumption / logs.size();

        double peakConsumption = logs.stream()
                .mapToDouble(EnergyLog::getConsumption)
                .max()
                .orElse(0);

        EnergyStatsDTO stats = new EnergyStatsDTO();
        stats.setDeviceId(deviceId);
        stats.setTotalConsumption(totalConsumption);
        stats.setAverageConsumption(averageConsumption);
        stats.setPeakConsumption(peakConsumption);
        stats.setStartDate(startDate);
        stats.setEndDate(endDate);

        return stats;
    }

    public EnergyStatsDTO getTotalEnergyStats(LocalDateTime startDate, LocalDateTime endDate) {
        List<EnergyLog> logs = energyLogRepository.findByTimestampBetween(startDate, endDate);

        double totalConsumption = logs.stream()
                .mapToDouble(EnergyLog::getConsumption)
                .sum();

        double averageConsumption = logs.isEmpty() ? 0 : totalConsumption / logs.size();

        double peakConsumption = logs.stream()
                .mapToDouble(EnergyLog::getConsumption)
                .max()
                .orElse(0);

        EnergyStatsDTO stats = new EnergyStatsDTO();
        stats.setTotalConsumption(totalConsumption);
        stats.setAverageConsumption(averageConsumption);
        stats.setPeakConsumption(peakConsumption);
        stats.setStartDate(startDate);
        stats.setEndDate(endDate);

        return stats;
    }

    public List<EnergyLog> getRecentLogs(int limit) {
        return energyLogRepository.findTopNByOrderByTimestampDesc(limit);
    }
}
