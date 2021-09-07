package com.catius.ojtproject.device.domain;

import com.catius.ojtproject.code.DeleteStatusCode;
import com.catius.ojtproject.code.StatusCode;
import com.catius.ojtproject.device.service.dto.DeviceDTO;

public class DeviceObjectMother {
    public static Device createDevice(){
        return Device.builder()
                .serialNumber("serial123")
                .macAddress("mac123")
                .qrCode("qr123")
                .statusCode(StatusCode.ACTIVE)
                .deleteStatusCode(DeleteStatusCode.FALSE)
                .version("0.0.1")
                .build();
    }

    public static DeviceDTO createDeviceDTO() {
        return DeviceDTO.builder()
                .serialNumber("serial123")
                .macAddress("mac123")
                .qrCode("qr123")
                .statusCode(StatusCode.ACTIVE)
                .deleteStatusCode(DeleteStatusCode.FALSE)
                .version("0.0.1")
                .build();
    }

    public static DeviceDTO editDeviceDTO(){
        return DeviceDTO.builder()
                .serialNumber("serial123")
                .macAddress("mac123")
                .qrCode("qr123")
                .statusCode(StatusCode.ACTIVE)
                .deleteStatusCode(DeleteStatusCode.FALSE)
                .version("0.0.1")
                .build();
    }

}
