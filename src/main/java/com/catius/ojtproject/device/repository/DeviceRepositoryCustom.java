package com.catius.ojtproject.device.repository;


import com.catius.ojtproject.device.domain.Device;
import com.catius.ojtproject.device.service.dto.DeviceDTO;

import java.util.List;

public interface DeviceRepositoryCustom {

    List<Device> findContaining(DeviceDTO deviceDTO);
}
