package com.catius.ojtproject.service;


import com.catius.ojtproject.code.DeleteStatusCode;
import com.catius.ojtproject.code.StatusCode;
import com.catius.ojtproject.domain.Device;
import com.catius.ojtproject.dto.CreateDevice;
import com.catius.ojtproject.dto.DeviceDetail;
import com.catius.ojtproject.dto.EditDevice;
import com.catius.ojtproject.repository.DeviceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;


@RunWith(MockitoJUnitRunner.class)
public class DeviceServiceImplTest {

    @Mock
    private DeviceRepository deviceRepository;

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
    public void getDeviceDetailSerialNuTmber() throws Exception {


        given(deviceRepository.findBySerialNumber(anyString()))
                .willReturn(Optional.of(device));

        DeviceDetail deviceDetail = deviceService.getDeviceDetailSerialNumber("213123asdsad123");

        assertEquals("213123asdsad123", deviceDetail.getSerialNumber());
        assertEquals("asdad123asd", deviceDetail.getMacAddress());
        assertEquals("https://www.naver.com/", deviceDetail.getQrCode());
        assertEquals(StatusCode.ACTIVE, deviceDetail.getStatusCode());
        assertEquals("0.0.1", deviceDetail.getVersion());

    }

    @Test
    public void editDeviceSerialNumber() {
        // given


        given(deviceRepository.findBySerialNumber(anyString()))
                .willReturn(Optional.of(device));

        //when
        EditDevice request = EditDevice.builder()
                .statusCode(StatusCode.INACTIVE)
                .version("0.0.1")
                .build();


        DeviceDetail deviceDetail = deviceService.editDeviceStatus("serial123",request);


        verify(deviceRepository, times(1)).findBySerialNumber("serial123");

        assertEquals(StatusCode.INACTIVE, deviceDetail.getStatusCode());


    }

    @Test
    public void deleteDeviceSerialNumber() throws Exception {
        //given

        String serialNumber = "213123asdsad123";

        given(deviceRepository.findBySerialNumber(anyString()))
                .willReturn(Optional.of(device));
        //when
        DeviceDetail deviceDetail = deviceService.deleteDeviceSerialNumber(serialNumber);

        //then
        verify(deviceRepository,times(1)).findBySerialNumber(serialNumber);

        ArgumentCaptor<String> captor =
                ArgumentCaptor.forClass(String.class);


        assertEquals(DeleteStatusCode.TRUE,deviceDetail.getDeleteStatusCode());
    }


    @Test
    public void getSearchDeviceSerialNumber() {

        //given
        List<Device> deviceList = new ArrayList<>();
        deviceList.add(device);
        given(deviceRepository.findBySerialNumberContaining(anyString()))
                .willReturn(deviceList);

        //when
        List<DeviceDetail> deviceDetails = deviceService.getSearchDeviceSerialNumber("asd");

        verify(deviceRepository,times(1)).findBySerialNumberContaining("asd");

        List<DeviceDetail> deviceDetailsResult = deviceList.stream().map(DeviceDetail::fromEntity).collect(Collectors.toList());

        assertEquals(device.getSerialNumber(),deviceDetailsResult.get(0).getSerialNumber());


    }

    @Test
    public void getSearchDeviceQrCode() {
        //given
        List<Device> deviceList = new ArrayList<>();
        deviceList.add(device);

        given(deviceRepository.findByQrCodeContaining(anyString()))
                .willReturn(deviceList);

        //when
        List<DeviceDetail> deviceDetails = deviceService.getSearchDeviceQrCode("naver");

        verify(deviceRepository,times(1)).findByQrCodeContaining("naver");

        List<DeviceDetail> deviceDetailsResult = deviceList.stream().map(DeviceDetail::fromEntity).collect(Collectors.toList());

        assertEquals(device.getQrCode(),deviceDetailsResult.get(0).getQrCode());

    }

    @Test
    public void getSearchDeviceMacAddress() {
        //given
        List<Device> deviceList = new ArrayList<>();
        deviceList.add(device);
        given(deviceRepository.findByMacAddressContaining(anyString()))
                .willReturn(deviceList);

        //when
        List<DeviceDetail> deviceDetails = deviceService.getSearchDeviceMacAddress("dad");

        verify(deviceRepository,times(1)).findByMacAddressContaining("dad");

        List<DeviceDetail> deviceDetailsResult = deviceList.stream().map(DeviceDetail::fromEntity).collect(Collectors.toList());

        assertEquals(device.getMacAddress(),deviceDetailsResult.get(0).getMacAddress());

    }
}
