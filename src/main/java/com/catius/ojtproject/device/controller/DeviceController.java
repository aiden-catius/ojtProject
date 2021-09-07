package com.catius.ojtproject.device.controller;


import com.catius.ojtproject.device.controller.request.DeviceCreateRequest;
import com.catius.ojtproject.device.controller.request.DevicesRequest;
import com.catius.ojtproject.device.controller.response.DeviceResponse;
import com.catius.ojtproject.device.service.DeviceService;
import com.catius.ojtproject.device.controller.request.EditDeviceRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/devices")
public class DeviceController {

    private final DeviceService deviceService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeviceResponse createDevice(
            @Valid @RequestBody DeviceCreateRequest request
    ){
        log.info("DeviceCreateRequest : {}", request);
        return DeviceResponse.convertResponse(deviceService.createDevice(DeviceCreateRequest.convertDTO(request)));
    }


    @GetMapping(value = "/{deviceId}")
    @ResponseStatus(HttpStatus.OK)
    public DeviceResponse getDevice(
            @PathVariable  Long deviceId){

        return DeviceResponse.convertResponse(deviceService.getDevice(deviceId));
    }


    @PutMapping("/{deviceId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public DeviceResponse editDevice(
            @PathVariable Long deviceId, @Valid @RequestBody EditDeviceRequest request){

        return DeviceResponse.convertResponse(deviceService.editDevice(deviceId,request));
    }

    @DeleteMapping("/{deviceId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteDevice(
            @PathVariable Long deviceId){
        deviceService.deleteDevice(deviceId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DeviceResponse> getDevices(
            @Valid DevicesRequest devicesRequest
    ){
        log.info("DevicesRequest : {}", devicesRequest);
        return DeviceResponse.convertResponseList(deviceService.getDevices(DevicesRequest.toDTO(devicesRequest)));
    }






}
