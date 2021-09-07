package com.catius.ojtproject.device.controller.request;

import com.catius.ojtproject.device.service.dto.DeviceDTO;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class DeviceCreateRequest {

        @NotEmpty(message = "serialNumber는 빈값 일 수 없습니다.")
        private String serialNumber;

        @NotEmpty(message = "macAddress는 빈값 일 수 없습니다")
        private String macAddress;

        @NotEmpty(message = "macAddress는 빈값 일 수 없습니다")
        private String qrCode;

        @NotEmpty
        private String version;

        public static com.catius.ojtproject.device.domain.Device convertEntity(DeviceCreateRequest deviceCreateRequest){
                return com.catius.ojtproject.device.domain.Device.builder()
                        .serialNumber(deviceCreateRequest.getSerialNumber())
                        .macAddress(deviceCreateRequest.getMacAddress())
                        .qrCode(deviceCreateRequest.getQrCode())
                        .version(deviceCreateRequest.getVersion())
                        .build();
        }

        public static DeviceDTO convertDTO(DeviceCreateRequest deviceCreateRequest){
                return DeviceDTO.builder()
                        .serialNumber(deviceCreateRequest.getSerialNumber())
                        .macAddress(deviceCreateRequest.getMacAddress())
                        .qrCode(deviceCreateRequest.getQrCode())
                        .version(deviceCreateRequest.getVersion())
                        .build();
        }



}
