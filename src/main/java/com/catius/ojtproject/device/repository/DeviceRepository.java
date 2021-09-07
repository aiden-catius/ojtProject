package com.catius.ojtproject.device.repository;

import com.catius.ojtproject.device.domain.Device;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device,Long>, DeviceRepositoryCustom {

    Optional<Device> findById(Long id);
    Optional<Device> findBySerialNumber(String serialNumber);

    @Query("select distinct d from Device d where d.serialNumber like %:serialNumber% or "
    + " d.macAddress like %:macAddress% or "
    + " d.qrCode like %:qrCode%")
    List<Device> findByIdContaining(@Param("serialNumber") String serialNumber,
                                    @Param("macAddress") String macAddress,
                                    @Param("qrCode") String qrCode);




}
