package com.catius.ojtproject.device.service;

import com.catius.ojtproject.device.controller.request.EditDeviceRequest;
import com.catius.ojtproject.device.domain.Device;
import com.catius.ojtproject.device.exception.DeviceException;
import com.catius.ojtproject.device.repository.DeviceRepository;
import com.catius.ojtproject.device.service.dto.DeviceDTO;
import com.catius.ojtproject.device.service.dto.DeviceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.catius.ojtproject.device.exception.DeviceErrorCode.*;
import static com.catius.ojtproject.device.repository.specification.DeviceSpecification.*;


@Service
@RequiredArgsConstructor
@Transactional
public class DeviceServiceImpl implements DeviceService {


    private final DeviceRepository deviceRepository;

    @Autowired
    DeviceService deviceService;


    @Transactional
    public DeviceDTO createDevice(DeviceDTO deviceDTO){

        deviceRepository.findBySerialNumber(deviceDTO.getSerialNumber()).ifPresent(device -> {
            throw new DeviceException(DUPLICATED_DEVICE_SERIALNUMBER);
        });


        return DeviceFactory.getDeviceDTO(deviceRepository.save(DeviceFactory.getCreateDevice(deviceDTO)));
    }




    @Transactional
    public DeviceDTO getDevice(Long deviceId){
        return DeviceFactory.getDeviceDTO(
                deviceRepository.findById(deviceId)
                        .orElseThrow(() -> new DeviceException(NO_DEVICE)));
    }

    @Transactional
    public DeviceDTO editDevice(Long deviceId, EditDeviceRequest request) {
        Device device = deviceRepository.findById(deviceId).orElseThrow(
                () -> new DeviceException(NO_DEVICE)
        );

        if(!device.inActiveDeivce()){
            throw new DeviceException(INVALID_REQUEST);
        }

        return DeviceFactory.getDeviceDTO(device);
    }

    @Transactional
    public DeviceDTO deleteDevice(Long deviceId) {
        Device device = deviceRepository.findById(deviceId).orElseThrow(
                () -> new DeviceException(NO_DEVICE)
        );

        if(!device.deleteDevice()){
            throw  new DeviceException(DELETED_DEVICE);
        }

        return DeviceFactory.getDeviceDTO(device);

    }






    @Transactional
    public List<DeviceDTO> getDevices(DeviceDTO deviceDTO) {


//       return DeviceFactory.getDeviceDTOs(deviceRepository.findAll(Specification.where(requestContain(deviceDTO))));

/*        return DeviceFactory.getDeviceDTOs(deviceRepository.findAll(Specification.where(macAddressContain(deviceDTO.getMacAddress()))
                .and(qrCodeContain(deviceDTO.getQrCode()))
                .and(serialNumberContain(deviceDTO.getSerialNumber()))
        ));*/

        return DeviceFactory.getDeviceDTOs(
                deviceRepository.findAll(Specification.where(
                                findAllContain(
                                        deviceDTO.getSerialNumber(),
                                        deviceDTO.getMacAddress(),
                                        deviceDTO.getQrCode())
                        )).stream()
                        .distinct()
                        .collect(Collectors.toList()));


//        return DeviceFactory.getDeviceDTOs(deviceRepositoryImpl.findContaining(deviceDTO));

/*        return deviceRepository.findByIdContaining(getDevicesServiceRequest.getSerialNumber(), getDevicesServiceRequest.getMacAddress(), getDevicesServiceRequest.getQrCode())
                .stream().map(DeviceDetail::fromEntity)
                .collect(Collectors.toList());*/
    }



    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public DeviceDTO createAndUpdate(DeviceDTO deviceDTO){

        DeviceDTO createDeviceDTO = createDevice(deviceDTO);

        System.out.println(createDeviceDTO.toString());

        DeviceDTO resultDto = null;
        try {
            resultDto = editDevice(1L, EditDeviceRequest.builder().build());
        }catch (Exception e){
            System.out.println(e);
        }


        System.out.println(resultDto.toString());

        return resultDto;

    }




}
