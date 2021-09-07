package com.catius.ojtproject.device.service;

import com.catius.ojtproject.device.exception.DeviceException;
import com.catius.ojtproject.device.repository.DeviceRepository;
import com.catius.ojtproject.device.repository.DeviceRepositoryImpl;
import com.catius.ojtproject.device.service.dto.DeviceDTO;
import com.catius.ojtproject.device.service.dto.DeviceFactory;
import com.catius.ojtproject.device.controller.request.EditDeviceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.catius.ojtproject.device.exception.DeviceErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final DeviceRepositoryImpl deviceRepositoryImple;


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
        com.catius.ojtproject.device.domain.Device device = deviceRepository.findById(deviceId).orElseThrow(
                () -> new DeviceException(NO_DEVICE)
        );

        if(!device.activeDeivce()){
            throw new DeviceException(INVALID_REQUEST);
        }

        return DeviceFactory.getDeviceDTO(device);
    }




    @Transactional
    public DeviceDTO deleteDevice(Long deviceId) {
        com.catius.ojtproject.device.domain.Device device = deviceRepository.findById(deviceId).orElseThrow(
                () -> new DeviceException(NO_DEVICE)
        );

        if(!device.deleteDevice()){
            // Todo 오류 코드 추가해서 던지기 (이미 삭제 처리된 디바이스)
            throw  new DeviceException(INVALID_REQUEST);
        }

        return DeviceFactory.getDeviceDTO(device);

    }

    @Override
    public List<DeviceDTO> getDevices(DeviceDTO deviceDTO) {

        return DeviceFactory.getDeviceDTOs(deviceRepositoryImple.findContaining(deviceDTO));

/*        return deviceRepository.findByIdContaining(getDevicesServiceRequest.getSerialNumber(), getDevicesServiceRequest.getMacAddress(), getDevicesServiceRequest.getQrCode())
                .stream().map(DeviceDetail::fromEntity)
                .collect(Collectors.toList());*/
    }



}
