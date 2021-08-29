package com.catius.ojtproject.service;

import com.catius.ojtproject.dto.CreateDevice;
import com.catius.ojtproject.dto.DeviceDetailDto;
import com.catius.ojtproject.dto.EditDeviceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class DeviceProxy implements DeviceService{

    private final RealDeviceService realDeviceService;


    @Override
    public CreateDevice.Response createDeviceSerialNumber(CreateDevice.Request request) {
       return realDeviceService.createDevice(request);
    }


    @Override
    public DeviceDetailDto getDeviceDetailSerialNumber(String serialNumber) {
        return realDeviceService.getDeviceDetailSerialNumber(serialNumber);
    }

    @Override
    public DeviceDetailDto getDeviceDetailQrCode(String qrCode) {
        return realDeviceService.getDeviceDetailQrCode(qrCode);
    }

    @Override
    public DeviceDetailDto getDeviceDetailMacAddress(String macAddress) {
        return realDeviceService.getDeviceDetailMacAddress(macAddress);
    }

    @Override
    public DeviceDetailDto editDeviceStatus(String serialNumber, EditDeviceRequest request) {


        return realDeviceService.editDeviceStatus(serialNumber,request);

    }

    @Override
    public void deleteDeivceSerialNumber(String serialNumber) {
            realDeviceService.deleteDeviceSerialNumber(serialNumber);
    }


}
