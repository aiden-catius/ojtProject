package com.catius.ojtproject.device.repository.specification;

import com.catius.ojtproject.device.domain.Device;
import com.catius.ojtproject.device.exception.DeviceErrorCode;
import com.catius.ojtproject.device.exception.DeviceException;
import com.catius.ojtproject.device.service.dto.DeviceDTO;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class DeviceSpecification{

    public static Specification<Device> serialNumberContain(String serialNumber){
        return (device, cq, cb) -> {
            if(serialNumber.isBlank() || serialNumber == null){
                 return null;
            }
           return cb.like(device.get("serialNumber"), "%" + serialNumber + "%");
        };
    }
    public static Specification<Device> macAddressContain(String macAddressContain){

        if(macAddressContain.isBlank() || macAddressContain == null){
            return null;
        }
        return (device, cq, cb) -> cb.like(device.get("macAddressContain"), "%" + macAddressContain + "%");
    }
    public static Specification<Device> qrCodeContain(String qrCode){

        if(qrCode.isBlank() || qrCode == null){
            return null;
        }
        return (device, cq, cb) -> cb.like(device.get("qrCode"), "%" + qrCode + "%");
    }


}
