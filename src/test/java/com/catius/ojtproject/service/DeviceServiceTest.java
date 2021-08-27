package com.catius.ojtproject.service;


import com.catius.ojtproject.code.StatusCode;
import com.catius.ojtproject.domain.Device;
import com.catius.ojtproject.dto.CreateDevice;
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
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class DeviceServiceTest {

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private DeviceService deviceService;




    @Test
    public void createDeviceTest_serialNumber() throws Exception {
        //given
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

       deviceService.createDeviceSerialNumber(request);

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


}
