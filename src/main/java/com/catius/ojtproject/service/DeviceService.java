package com.catius.ojtproject.service;

import com.catius.ojtproject.code.StatusCode;
import com.catius.ojtproject.domain.Device;
import com.catius.ojtproject.dto.CreateDevice;
import com.catius.ojtproject.dto.DeviceDetailDto;
import com.catius.ojtproject.dto.DeviceDto;
import com.catius.ojtproject.exception.DeviceException;
import com.catius.ojtproject.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.catius.ojtproject.code.StatusCode.*;
import static com.catius.ojtproject.exception.DeviceErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional
public class DeviceService {
    //프록시 패턴
    //인터페이스 구현

    private final DeviceRepository deviceRepository;

    @Transactional
    public void createDeviceSerialNumber(CreateDevice.Request request){
        Device device = Device.builder()
                .serialNumber(request.getSerialNumber())
                .macAddress(request.getMacAddress())
                .qrCode(request.getQrCode())
                .statusCode(ACTIVE)
                .build();

        deviceRepository.save(device);
    }

    public DeviceDetailDto getDeviceDetailSerialNumber(String serialNumber){
        return deviceRepository.findBySerialNumber(serialNumber)
                .map(DeviceDetailDto::fromEntity)
                .orElseThrow(() -> new DeviceException(NO_DEVICE));
    }



}
