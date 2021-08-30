package com.catius.ojtproject.controller;

import com.catius.ojtproject.service.DeviceServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

@WebMvcTest(DeviceController.class)
public class DeviceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeviceServiceImpl deviceServiceImpl;

    protected MediaType contentType =
            new MediaType(MediaType.APPLICATION_JSON.getSubtype(),
                    MediaType.APPLICATION_JSON.getSubtype(),
                    StandardCharsets.UTF_8);

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