package com.catius.ojtproject.repository;

import com.catius.ojtproject.domain.Device;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceRepository {
    Optional<Device> findBySerialNumber(String serialNumber);
    Optional<Device> findByQrCode(String qrCode);
    Optional<Device> findByMacAddress(String macAddress);



}
