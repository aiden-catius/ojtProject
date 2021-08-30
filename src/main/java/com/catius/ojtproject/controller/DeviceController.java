package com.catius.ojtproject.controller;


import com.catius.ojtproject.dto.CreateDevice;
import com.catius.ojtproject.dto.DeviceDetail;
import com.catius.ojtproject.dto.EditDevice;
import com.catius.ojtproject.service.DeviceService;
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
    public CreateDevice.Response createDevice(
            @Valid @RequestBody CreateDevice.Request request
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

    @GetMapping("/devices/{serialNumber}")
    public List<DeviceDetail> getSearchDevicesSerialNumber(
            @PathVariable String serialNumber
    ){
        return deviceService.getSearchDeviceSerialNumber(serialNumber);
    }
    @GetMapping("/devices/{macAddress}")
    public List<DeviceDetail> getSearchDevicesMacAddress(
            @PathVariable String macAddress
    ){
        return deviceService.getSearchDeviceMacAddress(macAddress);
    }
    @GetMapping("/devices/{qrCode}")
    public List<DeviceDetail> getSearchDevicesQrCode(
            @PathVariable String qrCode
    ){
        return deviceService.getSearchDeviceQrCode(qrCode);
    }




}
