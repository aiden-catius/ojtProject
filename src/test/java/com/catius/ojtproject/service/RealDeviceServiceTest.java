package com.catius.ojtproject.service;


import com.catius.ojtproject.code.StatusCode;
import com.catius.ojtproject.domain.Device;
import com.catius.ojtproject.dto.CreateDevice;
import com.catius.ojtproject.dto.DeviceDetailDto;
import com.catius.ojtproject.dto.EditDeviceRequest;
import com.catius.ojtproject.repository.DeviceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;


@RunWith(MockitoJUnitRunner.class)
public class RealDeviceServiceTest {

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private RealDeviceService realDeviceService;




    @Test
    public void createDevice() throws Exception {

        given(deviceRepository.findBySerialNumber(anyString()))
                .willReturn(Optional.empty());

        /**
         * ArgumentCaptor
         * mock객체가 동작을할때 파라미터로 받은 값을 캡쳐해
         * 캡쳐한 값이 검증에 활용할 수 있게 해준다.
         */
        ArgumentCaptor<Device> captor =
                ArgumentCaptor.forClass(Device.class);
        //when

        CreateDevice.Request request = CreateDevice.Request.builder()
                .serialNumber("213123asdsad123")
                .macAddress("asdad123asd")
                .qrCode("https://www.naver.com/")
                .statusCode(StatusCode.ACTIVE)
                .version("0.0.1")
                .build();


        realDeviceService.createDevice(request);

        //then
        /**
         * verify
         * 특정 mock이 몇번 호출되었다는 것을 검증 + repository에 save할때 넘어가는 파라미터를 capture할 수 있다.
         */


        verify(deviceRepository, times(1))
           .save(captor.capture());

        Device saveddevice = captor.getValue();

        assertEquals("213123asdsad123",saveddevice.getSerialNumber());
        assertEquals("asdad123asd",saveddevice.getMacAddress());
        assertEquals("https://www.naver.com/",saveddevice.getQrCode());


    }

    @Test
    public void getDeviceDetailSerialNumber() throws Exception {

        Device device = Device.builder()
                .serialNumber("213123asdsad123")
                .macAddress("asdad123asd")
                .qrCode("https://www.naver.com/")
                .statusCode(StatusCode.ACTIVE)
                .version("0.0.1")
                .build();

        given(deviceRepository.findBySerialNumber(anyString()))
                .willReturn(Optional.of(device));

        DeviceDetailDto deviceDetailDto = realDeviceService.getDeviceDetailSerialNumber("213123asdsad123");

        assertEquals("213123asdsad123",deviceDetailDto.getSerialNumber());
        assertEquals("asdad123asd",deviceDetailDto.getMacAddress());
        assertEquals("https://www.naver.com/",deviceDetailDto.getQrCode());
        assertEquals(StatusCode.ACTIVE,deviceDetailDto.getStatusCode());
        assertEquals("0.0.1",deviceDetailDto.getVersion());

    }

    @Test
    public void editDeviceSerialNumber() {
        // given
        Device device = Device.builder()
                .serialNumber("serial123")
                .macAddress("asdad123asd")
                .qrCode("https://www.naver.com/")
                .statusCode(StatusCode.ACTIVE)
                .version("0.0.1")
                .build();

        given(deviceRepository.findBySerialNumber(anyString()))
                .willReturn(Optional.of(device));

        //when
        EditDeviceRequest request = EditDeviceRequest.builder()
                .statusCode(StatusCode.INACTIVE)
                .version("0.0.1")
                .build();


        DeviceDetailDto deviceDetailDto = realDeviceService.editDeviceStatus("serial123",request);


        verify(deviceRepository, times(1)).findBySerialNumber("serial123");

        assertEquals(StatusCode.INACTIVE,deviceDetailDto.getStatusCode());


    }
}
