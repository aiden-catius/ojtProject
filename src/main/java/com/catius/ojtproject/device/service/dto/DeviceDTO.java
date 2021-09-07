package com.catius.ojtproject.device.service.dto;

import com.catius.ojtproject.code.DeleteStatusCode;
import com.catius.ojtproject.code.StatusCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter

public class DeviceDTO {

    private Long id;

    private String serialNumber;

    private String macAddress;

    private String qrCode;

    private StatusCode statusCode;

    private DeleteStatusCode deleteStatusCode;

    private String version;




}
