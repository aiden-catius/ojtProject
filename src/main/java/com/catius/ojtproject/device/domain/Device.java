package com.catius.ojtproject.device.domain;


import com.catius.ojtproject.code.DeleteStatusCode;
import com.catius.ojtproject.code.StatusCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(example = "디바이스 아이디")
    private Long id;

    @ApiModelProperty(example = "디바이스 시리얼번호")
    private String serialNumber;
    @ApiModelProperty(example = "디바이스 맥주소")
    private String macAddress;
    @ApiModelProperty(example = "디바이스 큐알코드")
    private String qrCode;

    @Enumerated(EnumType.STRING)
    @ApiModelProperty(example = "디바이스 상태")
    private StatusCode statusCode;

    @Enumerated(EnumType.STRING)
    @ApiModelProperty(example = "디바이스 폐기여부")
    private DeleteStatusCode deleteStatusCode;

    @ApiModelProperty(example = "디바이스 버전")
    private String version;

    @CreatedDate
    @ApiModelProperty(example = "디바이스 생성시간")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @ApiModelProperty(example = "디바이스 수정시간")
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

    public Boolean inActiveDeivce(){
        if(this.statusCode == StatusCode.ACTIVE){
            this.statusCode = StatusCode.INACTIVE;

            return true;
        } else {
            this.statusCode = StatusCode.INACTIVE;

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
