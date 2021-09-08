package com.catius.ojtproject.device.controller.request;


import com.catius.ojtproject.code.StatusCode;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EditDeviceRequest {


    @NotEmpty(message = "serialNumber는 빈값 일 수 없습니다.")
    private String serialNumber;

    @NotEmpty(message = "macAddress는 빈값 일 수 없습니다")
    private String macAddress;

    @NotEmpty(message = "macAddress는 빈값 일 수 없습니다")
    private String qrCode;

    @NotEmpty
    private String version;

    @NotNull
    private StatusCode statusCode;


}
