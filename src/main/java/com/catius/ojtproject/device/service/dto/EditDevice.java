package com.catius.ojtproject.device.service.dto;


import com.catius.ojtproject.code.StatusCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EditDevice {


    private String version;
    private StatusCode statusCode;


}
