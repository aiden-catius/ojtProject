package com.catius.ojtproject.dto;

import com.catius.ojtproject.code.DeleteStatusCode;
import com.catius.ojtproject.code.StatusCode;
import com.catius.ojtproject.domain.Device;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DeviceDetail {


    private String serialNumber;
    private String macAddress;
    private String qrCode;
    private StatusCode statusCode;
    private DeleteStatusCode deleteStatusCode;
    private String version;

    public static DeviceDetail fromEntity(Device device){

        return DeviceDetail.builder()
                .serialNumber(device.getSerialNumber())
                .macAddress(device.getMacAddress())
                .qrCode(device.getQrCode())
                .statusCode(device.getStatusCode())
                .deleteStatusCode(DeleteStatusCode.TRUE)
                .version(device.getVersion())
                .build();
    }


}
