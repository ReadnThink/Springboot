package com.Springboot.practice.parser;

import com.Springboot.practice.domain.dto.Hospital;

import java.time.LocalDateTime;
import java.util.Arrays;

public class HospitalParser implements Parser<Hospital> {
    @Override
    public Hospital parse(String str) {
//        String[] row = str.replace("\"","").split(",");
        String[] row = str.split("\",\"");
        System.out.println(Arrays.toString(row));
        Hospital hospital = new Hospital();

        //1 .id = 첫번쨰 " 따로처리
        hospital.setId(Integer.parseInt(row[0])); // StoI
        //2. 서비스명
        hospital.setOpenServiceName(row[1]);
        //3. 개방자치단체코드
        hospital.setOpenLocalGovernmentCode(Integer.parseInt(row[3]));
        //4. 관리번호
        hospital.setManagementNumber(row[4]);
        //5. 인허가일자
        int year = Integer.parseInt(row[5].substring(0,4));
        int month = Integer.parseInt(row[5].substring(4,6));
        int day = Integer.parseInt(row[5].substring(6,8));
//        System.out.printf("%d %d %d \n", year, month, day);
        hospital.setLicenseData(LocalDateTime.of(year,month,day,0,0,0)); //날짜 로직 처리해야함
        //6. 영업상태구분코드
        hospital.setBusinessStatus(Integer.parseInt(row[7]));
        //7. 상세영업상태코드
        hospital.setBusinessStatusCode(Integer.parseInt(row[9]));
        //8. 소재지 전화
        hospital.setPhone(row[15]);
        //9. 소재지 전체주소
        hospital.setFullAddress(row[18]);
        //10. 소재지 도로명주소
        hospital.setRoadNameAddress(row[19]);
        //11. 사업자명
        hospital.setHospitalName(row[21]);
        //12. 업태구분명 ex)치과의원, 한의원
        hospital.setBusinessTypeName(row[25]);
        //13. 입원실 수
        hospital.setHealthcareProviderCount(Integer.parseInt(row[29]));
        //15. 병상 수
        hospital.setPatientRoomCount(Integer.parseInt(row[30]));
        //14. 병상 수
        hospital.setTotalNumberOfBeds(Integer.parseInt(row[31]));
        //15. 총 면적
        hospital.setTotalAreaSize(Float.parseFloat(row[32]));


        return hospital;
    }
}
