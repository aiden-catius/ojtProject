package com.catius.ojtproject.service;

import com.catius.ojtproject.domain.Device;
import com.catius.ojtproject.dto.CreateDevice;
import com.catius.ojtproject.dto.DeviceDetailDto;
import com.catius.ojtproject.dto.EditDeviceRequest;
import com.catius.ojtproject.exception.DeviceException;
import org.springframework.transaction.annotation.Transactional;

import static com.catius.ojtproject.code.StatusCode.ACTIVE;
import static com.catius.ojtproject.exception.DeviceErrorCode.NO_DEVICE;

public interface DeviceService {


    CreateDevice.Response createDeviceSerialNumber(CreateDevice.Request request);
    DeviceDetailDto getDeviceDetailSerialNumber(String serialNumber);
    DeviceDetailDto getDeviceDetailQrCode(String qrCode);
    DeviceDetailDto getDeviceDetailMacAddress(String macAddress);
    DeviceDetailDto editDeviceStatus(String serialNumber, EditDeviceRequest request);
    void deleteDeivceSerialNumber(String serialNumber);


}
