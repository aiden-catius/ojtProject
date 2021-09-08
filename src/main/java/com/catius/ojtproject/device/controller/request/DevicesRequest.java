package com.catius.ojtproject.device.controller.request;

import com.catius.ojtproject.device.service.dto.DeviceDTO;
import lombok.*;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DevicesRequest {
    @Size(max=10)
    private String serialNumber;

    private String macAddress;

    private String qrCode;

    public static DeviceDTO toDTO(DevicesRequest devicesRequest){
        return DeviceDTO.builder()
                .serialNumber(devicesRequest.getSerialNumber())
                .macAddress(devicesRequest.getMacAddress())
                .qrCode(devicesRequest.getQrCode())
                .build();
    }

}
