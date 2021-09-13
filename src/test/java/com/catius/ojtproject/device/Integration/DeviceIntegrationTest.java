package com.catius.ojtproject.device.Integration;


import com.catius.ojtproject.code.DeleteStatusCode;
import com.catius.ojtproject.code.StatusCode;
import com.catius.ojtproject.config.CommonJpaTestConfiguration;
import com.catius.ojtproject.config.JpaUserConfiguration;
import com.catius.ojtproject.device.InitDb;
import com.catius.ojtproject.device.controller.request.EditDeviceRequest;
import com.catius.ojtproject.device.domain.Device;
import com.catius.ojtproject.device.domain.DeviceObjectMother;
import com.catius.ojtproject.device.service.DeviceService;
import com.catius.ojtproject.device.service.dto.DeviceDTO;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@ContextConfiguration( classes = {DeviceTestConfiguration.class}  )
@RunWith(SpringRunner.class)
public class DeviceIntegrationTest extends CommonJpaTestConfiguration {


    @Autowired
    DeviceService deviceService;



    private Gson gson;

    @Before
    public void setup() {gson = new Gson();}


    @Test
    @Transactional
    @Rollback(value = false)
    public void shouldCreateDeviceAndGetDevice() throws Exception{


        DeviceDTO deviceDTO = DeviceObjectMother.createDeviceDTO();
        DeviceDTO addDeviceDTO = deviceService.createDevice(DeviceObjectMother.createDeviceDTO());

        Assert.assertEquals(deviceDTO.getSerialNumber(),addDeviceDTO.getSerialNumber());
        Assert.assertEquals(deviceDTO.getMacAddress(),addDeviceDTO.getMacAddress());
        Assert.assertEquals(deviceDTO.getQrCode(),addDeviceDTO.getQrCode());

        System.out.println(addDeviceDTO.toString());

        DeviceDTO getDevice = deviceService.getDevice(1L);

        Assert.assertEquals(deviceDTO.getSerialNumber(),getDevice.getSerialNumber());
        Assert.assertEquals(deviceDTO.getMacAddress(),getDevice.getMacAddress());
        Assert.assertEquals(deviceDTO.getQrCode(),getDevice.getQrCode());


    }

    @Test
    @Transactional
    public void shouldEditAndDeleteDevice() throws Exception {

        deviceService.createDevice(DeviceObjectMother.createDeviceDTO());
        DeviceDTO updateDeviceDTO = deviceService.editDevice(1L, EditDeviceRequest.builder().build());

        Assert.assertEquals(StatusCode.INACTIVE,updateDeviceDTO.getStatusCode());

        DeviceDTO deleteDeviceDTO = deviceService.deleteDevice(1L);

        Assert.assertEquals(DeleteStatusCode.FALSE,deleteDeviceDTO.getDeleteStatusCode());


    }


    @Test
    @Transactional
    public void shouldGetDevices() throws Exception{

        List<DeviceDTO> devices = deviceService.getDevices(DeviceDTO.builder()
                        .macAddress("")
                        .serialNumber("a")
                        .qrCode("")
                .build());
        System.out.println(devices.stream().map(device -> device.toString()));


    }

    @Test
    @Rollback(false)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void transactionTest() throws Exception{

        try{
            deviceService.createAndUpdate(DeviceObjectMother.createDeviceDTO());
        }catch (Exception e){
            System.out.println(e);
        }

    }



}
