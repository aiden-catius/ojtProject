package com.catius.ojtproject.service;

import com.catius.ojtproject.dto.CreateDevice;
import com.catius.ojtproject.dto.DeviceDetail;
import com.catius.ojtproject.dto.EditDevice;

import java.util.List;

public interface DeviceService {


    CreateDevice.Response createDevice(CreateDevice.Request request);
    DeviceDetail getDeviceDetailSerialNumber(String serialNumber);
    DeviceDetail editDeviceStatus(String serialNumber, EditDevice request);
    DeviceDetail deleteDeviceSerialNumber(String serialNumber);

    List<DeviceDetail> getSearchDeviceSerialNumber(String search);
    List<DeviceDetail> getSearchDeviceMacAddress(String macAddress);
    List<DeviceDetail> getSearchDeviceQrCode(String qrCode);
}
