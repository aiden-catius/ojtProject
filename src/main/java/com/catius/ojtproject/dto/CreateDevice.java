package com.catius.ojtproject.dto;

import com.catius.ojtproject.code.StatusCode;
import com.catius.ojtproject.domain.Device;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;



public class CreateDevice {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    @ToString
    public static class Request{

        @NotBlank(message = "serialNumber는 빈값 일 수 없습니다.")
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
