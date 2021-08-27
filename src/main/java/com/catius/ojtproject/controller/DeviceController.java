package com.catius.ojtproject.controller;


import com.catius.ojtproject.dto.DeviceDetailDto;
import com.catius.ojtproject.dto.DeviceDto;
import com.catius.ojtproject.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeviceController {
    private final DeviceService deviceService;



    @PostMapping("device/{serialNumber}")
    public void createDevice(
            @PathVariable String serialNumber
    ){

    }

    @GetMapping("device/{serialNumber}")
    public DeviceDetailDto getDeviceDetailSerialNumber(
            @PathVariable  String serialNumber){

            return deviceService.getDeviceDetailSerialNumber(serialNumber);
    }







}
