package com.catius.ojtproject.device.service;

import com.catius.ojtproject.device.service.dto.DeviceDTO;
import com.catius.ojtproject.device.controller.request.EditDeviceRequest;

import java.util.List;

public interface DeviceService {

    DeviceDTO createDevice(DeviceDTO deviceDTO);
    DeviceDTO getDevice(Long deviceId);
    DeviceDTO editDevice(Long deviceId, EditDeviceRequest request);
    DeviceDTO deleteDevice(Long deviceId);


    List<DeviceDTO> getDevices(DeviceDTO deviceDTO);



}
