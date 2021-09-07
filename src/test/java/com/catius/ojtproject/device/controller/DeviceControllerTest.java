package com.catius.ojtproject.device.controller;

import com.catius.ojtproject.code.StatusCode;
import com.catius.ojtproject.device.controller.request.DeviceCreateRequest;
import com.catius.ojtproject.device.controller.request.EditDeviceRequest;
import com.catius.ojtproject.device.domain.DeviceObjectMother;
import com.catius.ojtproject.device.service.DeviceServiceImpl;
import com.catius.ojtproject.device.service.dto.DeviceDTO;
import com.catius.ojtproject.exception.DeviceExceptionHandler;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
public class DeviceControllerTest {

    public static final DeviceDTO RESPONSE = DeviceDTO.builder()
            .serialNumber("abc123")
            .qrCode("qrqr123")
            .macAddress("macmac123")
            .version("1.0.0")
            .build();

    public static final DeviceCreateRequest REQUEST = DeviceCreateRequest.builder()
            .serialNumber("abc123")
            .qrCode("qrqr123")
            .macAddress("macmac123")
            .version("1.0.0")
            .build();


    private MockMvc mockMvc;

    @Mock
    private DeviceServiceImpl deviceService;

    @Mock
    private StatusCode statusCode;

    @InjectMocks
    private DeviceController deviceController;

    private Gson gson;

    protected MediaType contentType =
            new MediaType(MediaType.APPLICATION_JSON.getSubtype(),
                    MediaType.APPLICATION_JSON.getSubtype(),
                    StandardCharsets.UTF_8);

    @Before
    public void setup() {
        gson = new Gson();
        statusCode = StatusCode.ACTIVE;

        mockMvc = MockMvcBuilders.standaloneSetup(deviceController)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .setControllerAdvice(new DeviceExceptionHandler())
                .build();
    }

    @Test
    public void shouldCreateDevice() throws Exception {
        when(deviceService.createDevice(any(DeviceDTO.class))).thenReturn(DeviceObjectMother.createDeviceDTO());

        mockMvc.perform(post("/devices")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .content(gson.toJson(DeviceCreateRequest.builder()
                                .serialNumber("serial123")
                                .qrCode("qr123")
                                .macAddress("mac123")
                                .version("1.0.0")
                                .build())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.serialNumber").value("serial123"))
                .andExpect(jsonPath("$.qrCode").value("qr123"))
                .andExpect(jsonPath("$.macAddress").value("mac123"))
                .andDo(print());
    }

    @Test
    public void shouldEditDevice() throws Exception {
        when(deviceService.editDevice(anyLong(), any(EditDeviceRequest.class))).thenReturn(DeviceObjectMother.editDeviceDTO());

        mockMvc.perform(put("/devices/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .content(gson.toJson(EditDeviceRequest.builder()
                                .serialNumber("serial123")
                                .qrCode("qr123")
                                .macAddress("mac123")
                                .version("1.0.0")
                                .statusCode(statusCode)
                                .build())))
//                .andExpect(jsonPath("$.statusCode").value("비활성"))
                .andExpect(status().isAccepted())
                .andDo(print());
    }


}