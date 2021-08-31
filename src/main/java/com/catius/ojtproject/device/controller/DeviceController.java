package com.catius.ojtproject.device.controller;


import com.catius.ojtproject.device.controller.request.DeviceSearchRequest;
import com.catius.ojtproject.device.controller.request.DeviceCreateRequest;
import com.catius.ojtproject.device.service.dto.DeviceDetail;
import com.catius.ojtproject.device.service.dto.EditDevice;
import com.catius.ojtproject.device.service.DeviceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @PostMapping("/device")
    public DeviceCreateRequest.Response createDevice(
            @Valid @RequestBody DeviceCreateRequest.Request request
    ){
        log.info("request : {}", request);
        return deviceService.createDevice(request);
    }

    @GetMapping("/device/{serialNumber}")
    public DeviceDetail getDeviceDetailSerialNumber(
            @PathVariable  String serialNumber){

        return deviceService.getDeviceDetailSerialNumber(serialNumber);
    }

    @PutMapping("/device/{serialNumber}")
    public DeviceDetail editDeviceStatus(
            @PathVariable String serialNumber, @Valid @RequestBody EditDevice request){

        return deviceService.editDeviceStatus(serialNumber,request);
    }

    @DeleteMapping("/device/{serialNumber}")
    public void deleteDevicSerialNumber(
            @PathVariable String serialNumber){

        deviceService.deleteDeviceSerialNumber(serialNumber);
    }

    @GetMapping("/devices/{id}")
    public List<DeviceDetail> getSearchDevicesSerialNumber(
            @PathVariable String serialNumber
    ){
        return deviceService.getSearchDeviceSerialNumber(serialNumber);
    }

    @PostMapping("/devices")
    public List<DeviceDetail> getSearchDevicesSerialNumber(
            @PathVariable String serialNumber
    ){
        return deviceService.getSearchDeviceSerialNumber(serialNumber);
    }

    @GetMapping("/devices")
    public List<DeviceDetail> getSearchDevicesSerialNumber(
            @RequestParam String serialNumber,
            @RequestParam String qrCode,
            @RequestParam String macAddress

    ){
        return deviceService.getSearchDevices(serialNumber, qrCode, macAddress);
    }

    @GetMapping("/devices")
    public List<DeviceDetail> getSearchDevicesSerialNumber(
            DeviceSearchRequest deviceSearchRequest


    ){
        return deviceService.getSearchDevices(deviceSearchRequest.toDto());
    }

    @GetMapping("/devices")
    public List<DeviceDetail> getSearchDevicesSerialNumber(
            @PathVariable String serialNumber
    ){
        return deviceService.getSearchDeviceSerialNumber(serialNumber);
    }

    @GetMapping("/devices/serialNumber/{serialNumber}")
    public List<DeviceDetail> getSearchDevicesSerialNumber(
            @PathVariable String serialNumber
    ){
        return deviceService.getSearchDeviceSerialNumber(serialNumber);
    }

    @GetMapping("/devices/{macAddress}")
    public List<DeviceDetail> getSearchDevicesMacAddress(
            @PathVariable String macAddress,
            @RequestParam String searchType
    ){
        return deviceService.getSearchDeviceMacAddress(searchType);
    }
    @GetMapping("/devices/qrCode/{qrCode}")
    public List<DeviceDetail> getSearchDevicesQrCode(
            @PathVariable String qrCode
    ){
        return deviceService.getSearchDeviceQrCode(qrCode);
    }





}
