package com.catius.ojtproject.device.repository;

import com.catius.ojtproject.device.domain.Device;
import com.catius.ojtproject.device.domain.DeviceObjectMother;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
// @DataJpaTest 어노이테이션을 사용하면, JPA 관련된 Component만 로드 된다.
@DataJpaTest
public class DeviceRepositoryTest {

    @Autowired
    private DeviceRepository deviceRepository;



    @Test
    public void shouldCreateDevice() throws Exception {
        //given
        Device device = DeviceObjectMother.createDevice();


        //when
        Device deviceResponse = deviceRepository.save(device);

        //then
        Assert.assertEquals(device.getSerialNumber(),deviceResponse.getSerialNumber());
    }

    @Test
    public void shouldGetDevice(){
        Device device = DeviceObjectMother.createDevice();

        deviceRepository.save(device);

        Optional<Device> deviceResult = deviceRepository.findById(1L);

        Assert.assertTrue(deviceResult.isPresent());
        Assert.assertEquals("serial123",deviceResult.get().getSerialNumber());


    }

    @Test
    public void shouldFailGetDevice(){
        Device device = DeviceObjectMother.createDevice();

        deviceRepository.save(device);

        Optional<Device> deviceResult = deviceRepository.findById(2L);

        Assert.assertTrue(deviceResult.isEmpty());
    }




}
