package com.catius.ojtproject.device.exception;

import lombok.Getter;

@Getter
public class DeviceException extends RuntimeException {
    private DeviceErrorCode deviceErrorCode;
    private String detailMessage;

    public DeviceException(DeviceErrorCode deviceErrorCode) {
        super(deviceErrorCode.getMessage());
        this.deviceErrorCode = deviceErrorCode;
    }

    public DeviceException(DeviceErrorCode deviceErrorCode, String detailMessage) {
        super(deviceErrorCode.getMessage());
        this.deviceErrorCode = deviceErrorCode;
        this.detailMessage = detailMessage;
    }
}
