package com.catius.ojtproject.device.repository.specification;

import com.catius.ojtproject.device.domain.Device;
import org.springframework.data.jpa.domain.Specification;

public class DeviceSpecification{

    public static Specification<Device> serialNumberContain(String serialNumber){

        if(serialNumber.isBlank() || serialNumber == null){
            return null;
        }

        return (device, cq, cb) ->  cb.like(device.get("serialNumber"), "%" + serialNumber + "%");

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
