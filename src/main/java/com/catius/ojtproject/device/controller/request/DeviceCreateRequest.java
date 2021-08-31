package com.catius.ojtproject.device.controller.request;

import com.catius.ojtproject.device.domain.Device;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


public class DeviceCreateRequest {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    @ToString
    public static class Request{

        @NotBlank(message = "serialNumber는 빈값 일 수 없습니다.")
        @NotEmpty
        private String serialNumber;

        @NotBlank(message = "macAddress는 빈값 일 수 없습니다")
        private String macAddress;

        @NotBlank(message = "macAddress는 빈값 일 수 없습니다")
        private String qrCode;

        @NotBlank
        private String version;

    }


    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    @ToString
    public static class Response{
        private String serialNumber;
        private String macAddress;
        private String qrCode;
        private String version;

        public static Response fromEntity(Device device){
            return Response.builder()
                    .serialNumber(device.getSerialNumber())
                    .macAddress(device.getMacAddress())
                    .qrCode(device.getQrCode())
                    .version(device.getVersion())
                    .build();
        }

    }

}
