package com.catius.ojtproject.device.controller.response;

import com.catius.ojtproject.code.DeleteStatusCode;
import com.catius.ojtproject.code.StatusCode;
import com.catius.ojtproject.device.service.dto.DeviceDTO;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DeviceResponse {


    private String serialNumber;
    private String macAddress;
    private String qrCode;
    private StatusCode statusCode;
    private DeleteStatusCode deleteStatusCode;
    private String version;

    public static DeviceResponse fromEntity(com.catius.ojtproject.device.domain.Device device){

        return com.catius.ojtproject.device.controller.response.DeviceResponse.builder()
                .serialNumber(device.getSerialNumber())
                .macAddress(device.getMacAddress())
                .qrCode(device.getQrCode())
                .statusCode(device.getStatusCode())
                .deleteStatusCode(DeleteStatusCode.TRUE)
                .version(device.getVersion())
                .build();
    }

    public static DeviceResponse convertResponse(DeviceDTO deviceDTO){
       return DeviceResponse.builder()
                .serialNumber(deviceDTO.getSerialNumber())
                .macAddress(deviceDTO.getMacAddress())
                .qrCode(deviceDTO.getQrCode())
                .version(deviceDTO.getVersion())
                .statusCode(deviceDTO.getStatusCode())
                .build();
    }


    public static List<DeviceResponse> convertResponseList(List<DeviceDTO> deviceDTOS){
        return deviceDTOS.stream().map(DeviceResponse::convertResponse).collect(Collectors.toList());
    }


}
