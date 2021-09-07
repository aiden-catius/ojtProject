package com.catius.ojtproject.device.service.dto;

import com.catius.ojtproject.code.DeleteStatusCode;
import com.catius.ojtproject.code.StatusCode;
import com.catius.ojtproject.device.controller.response.DeviceResponse;
import com.catius.ojtproject.device.domain.Device;

import java.util.List;
import java.util.stream.Collectors;


public class DeviceFactory {

    public static DeviceDTO getDeviceDTO(Device device) {
        return DeviceDTO.builder()
                .qrCode(device.getQrCode())
                .serialNumber(device.getSerialNumber())
                .macAddress(device.getMacAddress())
                .version(device.getVersion())
                .statusCode(device.getStatusCode())
                .deleteStatusCode(device.getDeleteStatusCode())
                .build();
    }

    public static Device getDevice(DeviceResponse deviceResponse){
        return Device.builder()
                .qrCode(deviceResponse.getQrCode())
                .serialNumber(deviceResponse.getSerialNumber())
                .macAddress(deviceResponse.getMacAddress())
                .version(deviceResponse.getVersion())
                .build();
    }

    public static Device getCreateDevice(DeviceDTO deviceDTO){
        return Device.builder()
                .qrCode(deviceDTO.getQrCode())
                .serialNumber(deviceDTO.getSerialNumber())
                .macAddress(deviceDTO.getMacAddress())
                .version(deviceDTO.getVersion())
                .statusCode(StatusCode.ACTIVE)
                .deleteStatusCode(DeleteStatusCode.TRUE)
                .build();
    }


    public static List<DeviceDTO> getDeviceDTOs(List<Device> devices){
        return devices.stream()
                .map(device -> getDeviceDTO(device))
                .collect(Collectors.toList());
    }

}
