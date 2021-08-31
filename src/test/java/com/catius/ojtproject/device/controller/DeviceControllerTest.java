package com.catius.ojtproject.device.controller;

import com.catius.ojtproject.device.controller.request.DeviceCreateRequest;
import com.catius.ojtproject.exception.DeviceExceptionHandler;
import com.catius.ojtproject.device.service.DeviceService;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.nio.charset.StandardCharsets;

@WebMvcTest(DeviceController.class)
@RunWith(SpringJUnit4ClassRunner.class)

public class DeviceControllerTest {

    public static final DeviceCreateRequest.Response RESPONSE = DeviceCreateRequest.Response.builder()
            .serialNumber("abc123")
            .qrCode("qrqr123")
            .macAddress("macmac123")
            .version("1.0.0")
            .build();

    public static final DeviceCreateRequest.Request REQUEST = DeviceCreateRequest.Request.builder()
            .serialNumber("abc123")
            .qrCode("qrqr123")
            .macAddress("macmac123")
            .version("1.0.0")
            .build();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeviceService deviceService;

    @InjectMocks
    private DeviceController deviceController;

    protected MediaType contentType =
            new MediaType(MediaType.APPLICATION_JSON.getSubtype(),
                    MediaType.APPLICATION_JSON.getSubtype(),
                    StandardCharsets.UTF_8);

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(deviceController)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .setControllerAdvice(new DeviceExceptionHandler())
                .build();
    }

    @Test
    public void createDevice() throws Exception {


        when(deviceService.createDevice(any(DeviceCreateRequest.Request.class))).thenReturn(RESPONSE);

/*
        MultiValueMap<String,String> request = new LinkedMultiValueMap<>();
        request.set("serialNumber",REQUEST.getSerialNumber() );
        request.set("macAddress",REQUEST.getMacAddress());
        request.set("qrCode",REQUEST.getQrCode());
        request.set("version",REQUEST.getVersion());
*/



        mockMvc.perform(MockMvcRequestBuilders
                        .post("/device/a").content( new Gson().toJson(

                        ))
                        );

    }

    @Test
    public void getDeviceDetailSerialNumber() {
    }

    @Test
    public void editDeviceStatus() {
    }

    @Test
    public void deleteDevicSerialNumber() {
    }

    @Test
    public void getSearchDevicesSerialNumber() {
    }

    @Test
    public void getSearchDevicesMacAddress() {
    }

    @Test
    public void getSearchDevicesQrCode() {
    }
}