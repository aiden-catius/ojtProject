package com.catius.ojtproject.device.domain;


import com.catius.ojtproject.code.DeleteStatusCode;
import com.catius.ojtproject.code.StatusCode;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String serialNumber;
    private String macAddress;
    private String qrCode;

    @Enumerated(EnumType.STRING)
    private StatusCode statusCode;
    @Enumerated(EnumType.STRING)
    private DeleteStatusCode deleteStatusCode;

    private String version;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;


    public Boolean activeDeivce(){
        if(this.statusCode == StatusCode.INACTIVE){
            this.statusCode = StatusCode.ACTIVE;

            return true;
        } else {
            this.statusCode = StatusCode.ACTIVE;

            return false;
        }

    }

    public Boolean deleteDevice(){
        if(this.deleteStatusCode == DeleteStatusCode.TRUE){
            this.deleteStatusCode = DeleteStatusCode.FALSE;

            return true;
        } else {
            this.deleteStatusCode = DeleteStatusCode.FALSE;

            return false;
        }
    }




}
