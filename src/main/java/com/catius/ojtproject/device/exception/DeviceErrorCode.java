package com.catius.ojtproject.device.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeviceErrorCode {
    NO_DEVICE("해당되는 디바이스가 없습니다."),
    DUPLICATED_DEVICE_ID("이미 등록되어있는 DeviceId입니다"),
    DUPLICATED_DEVICE_SERIALNUMBER("이미 등록되어있는 SerialNumber입니다"),
    DUPLICATED_MAC_ADDRESS("이미 등록되어있는 MacAddress입니다."),
    DUPLICATED_QRCOE("이미 등록되어있는 QRcode입니다"),
    INTERNAL_SERVER_ERROR("서버에 오류가 발생했습니다."),
    INVALID_REQUEST("잘못된 요청입니다"),
    INVALID_REQUEST_PARAM("유효한파라미터가 없습니다.");


    private String message;
}
