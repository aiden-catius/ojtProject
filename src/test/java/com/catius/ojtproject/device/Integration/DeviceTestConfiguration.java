package com.catius.ojtproject.device.Integration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.catius.ojtproject.device.service"
})
public class DeviceTestConfiguration {

}
