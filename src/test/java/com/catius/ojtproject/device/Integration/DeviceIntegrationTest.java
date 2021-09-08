package com.catius.ojtproject.device.Integration;


import com.catius.ojtproject.config.CommonJpaTestConfiguration;
import com.catius.ojtproject.device.domain.DeviceObjectMother;
import com.catius.ojtproject.device.service.DeviceService;
import com.catius.ojtproject.device.service.dto.DeviceDTO;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;



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
    public void souldAddDeviceAndGetDevice() throws Exception{



        DeviceDTO deviceDTO = DeviceObjectMother.createDeviceDTO();
        DeviceDTO addDeviceDTO = deviceService.createDevice(DeviceObjectMother.createDeviceDTO());

        System.out.println(deviceService.getDevice(1L));

        Assert.assertEquals(deviceDTO.getSerialNumber(),addDeviceDTO.getSerialNumber());
        Assert.assertEquals(deviceDTO.getMacAddress(),addDeviceDTO.getMacAddress());
        Assert.assertEquals(deviceDTO.getQrCode(),addDeviceDTO.getQrCode());



    }



}
