package com.catius.ojtproject.device;

import com.catius.ojtproject.device.domain.Device;
import com.catius.ojtproject.device.repository.DeviceRepository;
import com.catius.ojtproject.device.service.DeviceService;
import com.catius.ojtproject.device.service.dto.DeviceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;


@RequiredArgsConstructor
public class InitDb {


    private final DeviceService deviceService;

    @PostConstruct
    public void init(){
        dbInit1();
    }


    public void dbInit1(){
        deviceService.createDevice(DeviceDTO.builder()
                .serialNumber("serial123")
                .qrCode("qrqr123")
                .macAddress("macmac123")
                .version("1.1.0")
                .build());

        deviceService.createDevice(DeviceDTO.builder()
                .serialNumber("a")
                .qrCode("x")
                .macAddress("y")
                .version("1.1.0")
                .build());


        deviceService.createDevice(DeviceDTO.builder()
                .serialNumber("b")
                .qrCode("z")
                .macAddress("w")
                .version("1.1.0")
                .build());

        deviceService.createDevice(DeviceDTO.builder()
                .serialNumber("cc")
                .qrCode("abab")
                .macAddress("werwr")
                .version("1.1.0")
                .build());


        deviceService.createDevice(DeviceDTO.builder()
                .serialNumber("aa")
                .qrCode("fsfs")
                .macAddress("ryry")
                .version("1.1.0")
                .build());


    }



}
