package com.catius.ojtproject.device.service;

import com.catius.ojtproject.device.controller.request.DeviceSearchRequest;
import com.catius.ojtproject.device.controller.request.DeviceCreateRequest;
import com.catius.ojtproject.device.service.dto.DeviceDetail;
import com.catius.ojtproject.device.service.dto.EditDevice;

import java.util.List;

public interface DeviceService {


    DeviceCreateRequest.Response createDevice(DeviceCreateRequest.Request request);
    DeviceDetail getDeviceDetailSerialNumber(String serialNumber);
    DeviceDetail editDeviceStatus(String serialNumber, EditDevice request);
    DeviceDetail deleteDeviceSerialNumber(String serialNumber);

    List<DeviceDetail> getSearchDeviceSerialNumber(String search);
    List<DeviceDetail> getSearchDeviceMacAddress(String macAddress);
    List<DeviceDetail> getSearchDeviceQrCode(String qrCode);

    List<DeviceDetail> getSearchDevices(DeviceSearchRequest deviceSearchRequest);
}
