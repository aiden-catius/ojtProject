package com.catius.ojtproject.dto;

import com.catius.ojtproject.exception.DeviceErrorCode;
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
