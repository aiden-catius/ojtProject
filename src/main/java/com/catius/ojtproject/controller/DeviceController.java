package com.catius.ojtproject.controller;


import com.catius.ojtproject.dto.CreateDevice;
import com.catius.ojtproject.dto.DeviceDetailDto;
import com.catius.ojtproject.dto.EditDeviceRequest;
import com.catius.ojtproject.service.DeviceProxy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceProxy deviceProxy;

    @PostMapping("/device")
    public CreateDevice.Response createDevice(
            @Valid @RequestBody CreateDevice.Request request
    ){
        return deviceProxy.createDeviceSerialNumber(request);
    }

    @GetMapping("/device/{serialNumber}")
    public DeviceDetailDto getDeviceDetailSerialNumber(
            @PathVariable  String serialNumber){

        return deviceProxy.getDeviceDetailSerialNumber(serialNumber);
    }

    @GetMapping("/device/{qrCode}")
    public DeviceDetailDto getDeviceDetailQrCode(
            @PathVariable  String qrCode){

        return deviceProxy.getDeviceDetailQrCode(qrCode);
    }

    @GetMapping("/device/{macAddress}")
    public DeviceDetailDto getDeviceDetailMacAddress(
            @PathVariable  String macAddress){


        return deviceProxy.getDeviceDetailMacAddress(macAddress);
    }

    @PutMapping("/device/{serialNumber}")
    public DeviceDetailDto editDeviceStatus(
            @PathVariable String serialNumber, @Valid @RequestBody EditDeviceRequest request){

        return deviceProxy.editDeviceStatus(serialNumber,request);
    }
    @DeleteMapping("/device/{serialNumber}")
    public void deleteDevicSerialNumber(
            @PathVariable String serialNumber){

         deviceProxy.deleteDeivceSerialNumber(serialNumber);
    }




}
