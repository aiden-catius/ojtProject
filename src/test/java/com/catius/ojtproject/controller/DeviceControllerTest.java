package com.catius.ojtproject.controller;

import com.catius.ojtproject.service.DeviceServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.nio.charset.StandardCharsets;

@WebMvcTest(DeviceController.class)
public class DeviceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeviceServiceImpl deviceServiceImpl;

    @InjectMocks
    private DeviceController deviceController;

    protected MediaType contentType =
            new MediaType(MediaType.APPLICATION_JSON.getSubtype(),
                    MediaType.APPLICATION_JSON.getSubtype(),
                    StandardCharsets.UTF_8);

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());

        mockMvc = MockMvcBuilders.standaloneSetup(deviceController)
                .setControllerAdvice(new ExceptionHandlerExceptionResolver())
                .build();


    }

    @Test
    public void createDevice() {
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