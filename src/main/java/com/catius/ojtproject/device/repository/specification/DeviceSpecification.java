package com.catius.ojtproject.device.repository.specification;

import com.catius.ojtproject.device.domain.Device;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class DeviceSpecification{



    public static Specification<Device> findAllContain(String serialNumber,String macAddress, String qrCode){

        return (device, cq, cb) ->  {
            List<Predicate> predicates = new ArrayList<>();

            if(!macAddress.isBlank() && macAddress != null){
               predicates.add(cb.like(device.get("macAddress"),"%" + macAddress + "%" ));
            }

            if(!qrCode.isBlank() && qrCode != null){
                predicates.add(cb.like(device.get("qrCode"),"%" + qrCode + "%" ));
            }

            if(!serialNumber.isBlank() && serialNumber != null){
                predicates.add(cb.like(device.get("serialNumber"),"%" + serialNumber + "%" ));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

    }


    public static Specification<Device> serialNumberContain(String serialNumber){

        if(serialNumber.isBlank() || serialNumber == null){
            return null;
        }

        return (device, cq, cb) ->  cb.like(device.get("serialNumber"), "%" + serialNumber + "%");

    }
    public static Specification<Device> macAddressContain(String macAddress){

        if(macAddress.isBlank() || macAddress == null){
            return null;
        }
        return (device, cq, cb) -> cb.like(device.get("macAddress"), "%" + macAddress + "%");
    }
    public static Specification<Device> qrCodeContain(String qrCode){

        if(qrCode.isBlank() || qrCode == null){
            return null;
        }
        return (device, cq, cb) -> cb.like(device.get("qrCode"), "%" + qrCode + "%");
    }


}
