package com.catius.ojtproject.device.service.dto;

import com.catius.ojtproject.device.exception.DeviceErrorCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceErrorDetail {

    private DeviceErrorCode errorCode;
    private String errorMessage;


}
