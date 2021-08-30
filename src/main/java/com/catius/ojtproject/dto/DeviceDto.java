package com.catius.ojtproject.dto;

import com.catius.ojtproject.code.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDto {

    private String serialNumber;
    private String macAddress;
    private String qrCode;
    private StatusCode statusCode;
    private String version;




}
