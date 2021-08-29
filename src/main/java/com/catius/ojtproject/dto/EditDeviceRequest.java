package com.catius.ojtproject.dto;


import com.catius.ojtproject.code.StatusCode;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EditDeviceRequest {


    private String version;
    private StatusCode statusCode;


}
