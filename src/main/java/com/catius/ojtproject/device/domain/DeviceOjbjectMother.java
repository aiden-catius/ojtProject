package com.catius.ojtproject.device.domain;

import com.catius.ojtproject.code.DeleteStatusCode;
import com.catius.ojtproject.code.StatusCode;

public class DeviceOjbjectMother {
    public static Device createDevice(){
        return Device.builder()
                .serialNumber("213123asdsad123")
                .macAddress("asdad123asd")
                .qrCode("https://www.naver.com/")
                .statusCode(StatusCode.ACTIVE)
                .deleteStatusCode(DeleteStatusCode.FALSE)
                .version("0.0.1")
                .build();
    }
}
