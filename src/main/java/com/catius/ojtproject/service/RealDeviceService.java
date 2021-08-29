package com.catius.ojtproject.service;

import com.catius.ojtproject.code.StatusCode;
import com.catius.ojtproject.domain.Device;
import com.catius.ojtproject.dto.CreateDevice;
import com.catius.ojtproject.dto.DeviceDetailDto;
import com.catius.ojtproject.dto.DeviceDto;
import com.catius.ojtproject.dto.EditDeviceRequest;
import com.catius.ojtproject.exception.DeviceErrorCode;
import com.catius.ojtproject.exception.DeviceException;
import com.catius.ojtproject.repository.DeviceRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.catius.ojtproject.code.StatusCode.*;
import static com.catius.ojtproject.exception.DeviceErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional
public class RealDeviceService {

    private final DeviceRepository deviceRepository;


    @Transactional
    public CreateDevice.Response createDevice(CreateDevice.Request request){
        Device device = Device.builder()
                .serialNumber(request.getSerialNumber())
                .macAddress(request.getMacAddress())
                .qrCode(request.getQrCode())
                .statusCode(ACTIVE)
                .version(request.getVersion())
                .build();


        deviceRepository.save(device);

        return CreateDevice.Response.fromEntity(device);
    }

    @Transactional
    public DeviceDetailDto getDeviceDetailSerialNumber(String serialNumber){
        return deviceRepository.findBySerialNumber(serialNumber)
                .map(DeviceDetailDto::fromEntity)
                .orElseThrow(() -> new DeviceException(NO_DEVICE));
    }

    @Transactional
    public DeviceDetailDto getDeviceDetailMacAddress(String macAddress){
        return deviceRepository.findByMacAddress(macAddress)
                .map(DeviceDetailDto::fromEntity)
                .orElseThrow(() -> new DeviceException(NO_DEVICE));
    }

    @Transactional
    public DeviceDetailDto getDeviceDetailQrCode(String qrCode){
        return deviceRepository.findByQrCode(qrCode)
                .map(DeviceDetailDto::fromEntity)
                .orElseThrow(() -> new DeviceException(NO_DEVICE));
    }


    @Transactional
    public DeviceDetailDto editDeviceStatus(String serialNumber, EditDeviceRequest request) {
        Device device = deviceRepository.findBySerialNumber(serialNumber).orElseThrow(
                () -> new DeviceException(NO_DEVICE)
        );

        device.setStatusCode(request.getStatusCode());

        return DeviceDetailDto.fromEntity(device);
    }


    @Transactional
    public void deleteDeviceSerialNumber(String serialNumber) {
        Device device = deviceRepository.findBySerialNumber(serialNumber).orElseThrow(
                () -> new DeviceException(NO_DEVICE)
        );
        deviceRepository.delete(device);
    }
}
