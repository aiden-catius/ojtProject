package com.catius.ojtproject.device.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeviceSearchRequest {

    private String type;
    private String serialNumber;
    private String qrCode;
    private String macAddress;


    public DeviceSearchRequest toDto() {
    }
}
