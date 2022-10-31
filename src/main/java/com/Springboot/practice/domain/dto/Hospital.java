package com.Springboot.practice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor //Lombok사용 : 컬럼이 추가되거나 제거되어도 Constructor, getter를 추가로 만들거나 지우지 않아도 됨
@Getter
@Setter
@NoArgsConstructor
public class Hospital {
    private int id;
    private String openServiceName; //Calmel case
    private int openLocalGovernmentCode;
    private String managementNumber;
    private LocalDateTime licenseData;
    private int businessStatus;
    private int businessStatusCode;
    private String phone;
    private String fullAddress;
    private String roadNameAddress;
    private String hospitalName;
    private String businessTypeName;
    private int healthcareProviderCount;
    private int patientRoomCount;
    private int totalNumberOfBeds;
    private float totalAreaSize;

}
