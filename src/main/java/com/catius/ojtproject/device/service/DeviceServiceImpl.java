package com.catius.ojtproject.device.service;

import com.catius.ojtproject.code.DeleteStatusCode;
import com.catius.ojtproject.device.domain.Device;
import com.catius.ojtproject.device.controller.request.DeviceCreateRequest;
import com.catius.ojtproject.device.service.dto.DeviceDetail;
import com.catius.ojtproject.device.service.dto.EditDevice;
import com.catius.ojtproject.device.exception.DeviceException;
import com.catius.ojtproject.device.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.catius.ojtproject.code.StatusCode.*;
import static com.catius.ojtproject.device.exception.DeviceErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;


    @Transactional
    public DeviceCreateRequest.Response createDevice(DeviceCreateRequest.Request request){
        Device newDevice = Device.builder()
                .serialNumber(request.getSerialNumber())
                .macAddress(request.getMacAddress())
                .qrCode(request.getQrCode())
                .statusCode(ACTIVE)
                .version(request.getVersion())
                .build();

        deviceRepository.findBySerialNumber(request.getSerialNumber()).ifPresent(device -> {
            throw new DeviceException(DUPLICATED_DEVICE_SERIALNUMBER);
        });


        deviceRepository.save(newDevice);

        return DeviceCreateRequest.Response.fromEntity(newDevice);
    }


    @Transactional
    public DeviceDetail getDeviceDetailSerialNumber(String serialNumber){
        return deviceRepository.findBySerialNumber(serialNumber)
                .map(DeviceDetail::fromEntity)
                .orElseThrow(() -> new DeviceException(NO_DEVICE));
    }

    @Transactional
    public DeviceDetail getDeviceDetailMacAddress(String macAddress){
        return deviceRepository.findByMacAddress(macAddress)
                .map(DeviceDetail::fromEntity)
                .orElseThrow(() -> new DeviceException(NO_DEVICE));
    }

    @Transactional
    public DeviceDetail getDeviceDetailQrCode(String qrCode){
        return deviceRepository.findByQrCode(qrCode)
                .map(DeviceDetail::fromEntity)
                .orElseThrow(() -> new DeviceException(NO_DEVICE));
    }


    @Transactional
    public DeviceDetail editDeviceStatus(String serialNumber, EditDevice request) {
        Device device = deviceRepository.findBySerialNumber(serialNumber).orElseThrow(
                () -> new DeviceException(NO_DEVICE)
        );

        device.setStatusCode(request.getStatusCode());

        return DeviceDetail.fromEntity(device);
    }


    @Transactional
    public DeviceDetail deleteDeviceSerialNumber(String serialNumber) {
        Device device = deviceRepository.findBySerialNumber(serialNumber).orElseThrow(
                () -> new DeviceException(NO_DEVICE)
        );

        device.setDeleteStatusCode(DeleteStatusCode.FALSE);

        return DeviceDetail.fromEntity(device);

    }

    @Transactional
    public List<DeviceDetail> getSearchDeviceSerialNumber(String serialNumber) {
        List<DeviceDetail> deviceSearchListSerialNumber = new ArrayList<>();


        return deviceRepository.findBySerialNumberContaining(serialNumber)
                .stream().map(DeviceDetail::fromEntity)
                .collect(Collectors.toList());

    }

    @Override
    public List<DeviceDetail> getSearchDeviceMacAddress(String macAddress) {
        List<DeviceDetail> deviceSearchListMacAddress = new ArrayList<>();

        deviceSearchListMacAddress = deviceRepository.findByMacAddressContaining(macAddress)
                .stream().map(DeviceDetail::fromEntity)
                .collect(Collectors.toList());

        return deviceSearchListMacAddress;
    }

    @Override
    public List<DeviceDetail> getSearchDeviceQrCode(String qrCode) {
        List<DeviceDetail> deviceSearchListQrCode = new ArrayList<>();

        deviceSearchListQrCode = deviceRepository.findByQrCodeContaining(qrCode)
                .stream().map(DeviceDetail::fromEntity)
                .collect(Collectors.toList());

        return deviceSearchListQrCode;
    }


}
