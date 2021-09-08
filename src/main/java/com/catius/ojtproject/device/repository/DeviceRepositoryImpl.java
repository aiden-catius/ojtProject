package com.catius.ojtproject.device.repository;

import com.catius.ojtproject.device.domain.Device;
import com.catius.ojtproject.device.exception.DeviceErrorCode;
import com.catius.ojtproject.device.exception.DeviceException;
import com.catius.ojtproject.device.service.dto.DeviceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DeviceRepositoryImpl implements DeviceRepositoryCustom {

    private final EntityManager em;

    @Override
    public List<Device> findContaining(DeviceDTO deviceDTO) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Device> cq = cb.createQuery(Device.class);

        Root<Device> device = cq.from(Device.class);

        List<Predicate> predicates = new ArrayList<>();



        if(!deviceDTO.getMacAddress().isBlank() && deviceDTO.getMacAddress() != null){
            predicates.add(cb.like(device.get("macAddress"),"%" + deviceDTO.getMacAddress() + "%" ));
        }

        if(!deviceDTO.getQrCode().isBlank() && deviceDTO.getQrCode() != null){
            predicates.add(cb.like(device.get("qrCode"),"%" + deviceDTO.getQrCode() + "%" ));
        }

        if(!deviceDTO.getSerialNumber().isBlank() && deviceDTO.getSerialNumber() != null){
            predicates.add(cb.like(device.get("serialNumber"),"%" + deviceDTO.getSerialNumber() + "%" ));
        }


        if(predicates.isEmpty()){
            throw new DeviceException(DeviceErrorCode.INVALID_REQUEST_PARAM);
        }

        cq.where(predicates.toArray(new Predicate[0])).distinct(true);

        return em.createQuery(cq).getResultList();


    }
}
