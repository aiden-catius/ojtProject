package com.catius.ojtproject.device.controller;


import com.catius.ojtproject.device.controller.request.DeviceCreateRequest;
import com.catius.ojtproject.device.controller.request.DevicesRequest;
import com.catius.ojtproject.device.controller.response.DeviceResponse;
import com.catius.ojtproject.device.domain.DeviceObjectMother;
import com.catius.ojtproject.device.service.DeviceService;
import com.catius.ojtproject.device.controller.request.EditDeviceRequest;
import com.catius.ojtproject.device.service.dto.DeviceFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    //201 요청이 성공적이였으며 그결과로 새로운 리소스가 생성되었습니다.
    //일반적으로 POST요청 또는 일부 PUT요청 이후에 따라온다.
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "DeviceResponse")
    public DeviceResponse createDevice(
            @Valid @RequestBody DeviceCreateRequest request
    ){
        log.info("DeviceCreateRequest : {}", request);
        return DeviceResponse.convertResponse(deviceService.createDevice(DeviceCreateRequest.convertDTO(request)));
    }


    @GetMapping(value = "/{deviceId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "getDevice")
    public DeviceResponse getDevice(
            @PathVariable  Long deviceId){

        return DeviceResponse.convertResponse(deviceService.getDevice(deviceId));
    }


    @PutMapping("/{deviceId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "editDevice")
    public DeviceResponse editDevice(
            @PathVariable Long deviceId, @Valid @RequestBody EditDeviceRequest request){

        return DeviceResponse.convertResponse(deviceService.editDevice(deviceId,request));
    }

    @DeleteMapping("/{deviceId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "deleteDevice")
    public void deleteDevice(
            @PathVariable Long deviceId){
        deviceService.deleteDevice(deviceId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "getDevices")
    public List<DeviceResponse> getDevices(
            @Valid DevicesRequest devicesRequest
    ){
        log.info("DevicesRequest : {}", devicesRequest);
        return DeviceResponse.convertResponseList(deviceService.getDevices(DevicesRequest.toDTO(devicesRequest)));
    }

    @PostMapping("/transaction")
    @ApiOperation(value = "DeviceResponse")
    public DeviceResponse transactionDevice(){
        return DeviceResponse.convertResponse(deviceService.createAndUpdate(DeviceObjectMother.createDeviceDTO()));
    }




}
