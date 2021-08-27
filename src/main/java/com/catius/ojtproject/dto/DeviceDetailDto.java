package com.catius.ojtproject.dto;

import com.catius.ojtproject.code.StatusCode;
import com.catius.ojtproject.domain.Device;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DeviceDetailDto {


    private String serialNumber;
    private String macAddress;
    private String qrCode;
    private StatusCode statusCode;
    private String version;

    public static DeviceDetailDto fromEntity(Device device){

        return DeviceDetailDto.builder()
                .serialNumber(device.getSerialNumber())
                .macAddress(device.getMacAddress())
                .qrCode(device.getQrCode())
                .statusCode(device.getStatusCode())
                .version(device.getVersion())
                .build();
    }

}
