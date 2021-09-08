package com.catius.ojtproject.device.service;


import com.catius.ojtproject.code.DeleteStatusCode;
import com.catius.ojtproject.code.StatusCode;
import com.catius.ojtproject.device.domain.Device;
import com.catius.ojtproject.device.domain.DeviceObjectMother;
import com.catius.ojtproject.device.exception.DeviceErrorCode;
import com.catius.ojtproject.device.exception.DeviceException;
import com.catius.ojtproject.device.controller.response.DeviceResponse;
import com.catius.ojtproject.device.repository.DeviceRepositoryImpl;
import com.catius.ojtproject.device.service.dto.DeviceDTO;
import com.catius.ojtproject.device.service.dto.DeviceFactory;
import com.catius.ojtproject.device.controller.request.EditDeviceRequest;
import com.catius.ojtproject.device.repository.DeviceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class DeviceServiceImplTest {

    @Mock
    private DeviceRepository deviceRepository;

    @Mock
    private DeviceRepositoryImpl deviceRepositoryImpl;

    @InjectMocks
    private DeviceServiceImpl deviceService;

    private final Device device = Device.builder()
            .serialNumber("213123asdsad123")
            .macAddress("asdad123asd")
            .qrCode("https://www.naver.com/")
            .statusCode(StatusCode.ACTIVE)
            .deleteStatusCode(DeleteStatusCode.FALSE)
            .version("0.0.1")
            .build();

    public static final EditDeviceRequest EditRequest = EditDeviceRequest.builder()
            .serialNumber("serial123")
            .qrCode("qr123")
            .macAddress("mac123")
            .version("1.0.0")
            .statusCode(StatusCode.ACTIVE)
            .build();



    @Test
    public void  shouldCreateDevice() throws Exception {

        given(deviceRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        /**
         * ArgumentCaptor
         * mock객체가 동작을할때 파라미터로 받은 값을 캡쳐해
         * 캡쳐한 값이 검증에 활용할 수 있게 해준다.
         */
        ArgumentCaptor<Device> captor =
                ArgumentCaptor.forClass(Device.class);
        //when

        DeviceDTO request = DeviceDTO.builder()
                .serialNumber("213123asdsad123")
                .macAddress("asdad123asd")
                .qrCode("https://www.naver.com/")
                .version("0.0.1")
                .build();


        deviceService.createDevice(request);

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
    public void shouldGetDevice() throws Exception {


        given(deviceRepository.findBySerialNumber(anyString()))
                .willReturn(Optional.of(device));

        DeviceDTO deviceDTO = deviceService.getDevice(123L);

        DeviceResponse response  = DeviceResponse.convertResponse(deviceDTO);

        assertEquals("213123asdsad123", response.getSerialNumber());
        assertEquals("asdad123asd", response.getMacAddress());
        assertEquals("https://www.naver.com/", response.getQrCode());
        assertEquals(StatusCode.ACTIVE, response.getStatusCode());
        assertEquals("0.0.1", response.getVersion());

    }

    @Test
    public void editDeviceSerialNumber() {
        // given


        given(deviceRepository.findBySerialNumber(anyString()))
                .willReturn(Optional.of(device));

        //when
        EditDeviceRequest request = EditDeviceRequest.builder()
                .statusCode(StatusCode.INACTIVE)
                .version("0.0.1")
                .build();


        DeviceDTO deviceDTO = deviceService.editDevice(1L,request);


        verify(deviceRepository, times(1)).findBySerialNumber("serial123");

        assertEquals(StatusCode.INACTIVE, DeviceResponse.convertResponse(deviceDTO).getStatusCode());


    }

    @Test
    public void deleteDevice() throws Exception {
        //given

        Long memberId = 1L;

        given(deviceRepository.findBySerialNumber(anyString()))
                .willReturn(Optional.of(device));
        //when
        DeviceDTO deviceDTO = deviceService.deleteDevice(memberId);


        DeviceResponse deviceResponse = DeviceResponse.convertResponse(deviceDTO);

        //then
        verify(deviceRepository,times(1)).findById(memberId);

        ArgumentCaptor<String> captor =
                ArgumentCaptor.forClass(String.class);


        assertEquals(DeleteStatusCode.TRUE, deviceResponse.getDeleteStatusCode());
    }


    @Test
    public void getDeivecs() throws Exception {
        Device device = DeviceObjectMother.createDevice();

       /*  Device.builder()
                .serialNumber("serial123")
                .macAddress("mac123")
                .qrCode("qr123")
                .statusCode(StatusCode.ACTIVE)
                .deleteStatusCode(DeleteStatusCode.FALSE)
                .version("0.0.1")
                .build();*/

        when(deviceRepositoryImpl.findContaining(any(DeviceDTO.class)))
                .thenReturn(Arrays.asList(device));


        //when
        List<DeviceDTO> deviceDTOS = DeviceFactory.getDeviceDTOs(deviceRepositoryImpl.findContaining(DeviceDTO.builder()
                .serialNumber("a")
                .build()));

        //then
        assertEquals(1,deviceDTOS.size());
        assertEquals("serial123",deviceDTOS.get(0).getSerialNumber());
    }

    @Test
    public void shouldFailToEditDeviceWhenNoUser()throws Exception {

        when(deviceRepository.findById(anyLong())).thenReturn(Optional.empty());


        DeviceException deviceException = assertThrows(DeviceException.class,
                () -> deviceService.editDevice(1L,EditRequest)
        );

        assertEquals(DeviceErrorCode.NO_DEVICE,deviceException.getDeviceErrorCode());
    }





}
