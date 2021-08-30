package com.catius.ojtproject.repository;

import com.catius.ojtproject.domain.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device,Long> {
    Optional<Device> findBySerialNumber(String serialNumber);
    Optional<Device> findByQrCode(String qrCode);
    Optional<Device> findByMacAddress(String macAddress);

    List<Device> findBySerialNumberContaining(String serialNumber);
    List<Device> findByQrCodeContaining(String qrCode);
    List<Device> findByMacAddressContaining(String macAddress);


}
