package com.catius.ojtproject.dto;

import com.catius.ojtproject.code.StatusCode;
import com.catius.ojtproject.domain.Device;
import lombok.*;
import javax.validation.constraints.NotNull;



public class CreateDevice {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    @ToString
    public static class Request{

        @NotNull
        private String serialNumber;
        @NotNull
        private String macAddress;
        @NotNull
        private String qrCode;
        @NotNull
        private StatusCode statusCode;
        @NotNull
        private String version;

    }


    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class Response{
        private String serialNumber;
        private String macAddress;
        private String qrCode;
        private StatusCode statusCode;
        private String version;

        public static Response fromEntity(Device device){
            return Response.builder()
                    .serialNumber(device.getSerialNumber())
                    .macAddress(device.getMacAddress())
                    .qrCode(device.getQrCode())
                    .statusCode(device.getStatusCode())
                    .version(device.getVersion())
                    .build();
        }

    }

}
