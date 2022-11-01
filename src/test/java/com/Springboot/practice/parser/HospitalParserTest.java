package com.Springboot.practice.parser;

import com.Springboot.practice.dao.HospitalDao;
import com.Springboot.practice.domain.dto.Hospital;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest//(classes = ParserFactory.class)// new 연산자를 사용하지 않아도 해당 클래스의 Bean을 찾아 실행한다.
class HospitalParserTest {

    String line1 = "\"1\",\"의원\",\"01_01_02_P\",\"3620000\",\"PHMA119993620020041100004\",\"19990612\",\"\",\"01\",\"영업/정상\",\"13\",\"영업중\",\"\",\"\",\"\",\"\",\"062-515-2875\",\"\",\"500881\",\"광주광역시 북구 풍향동 565번지 4호 3층\",\"광주광역시 북구 동문대로 24, 3층 (풍향동)\",\"61205\",\"효치과의원\",\"20211115113642\",\"U\",\"2021-11-17 02:40:00.0\",\"치과의원\",\"192630.735112\",\"185314.617632\",\"치과의원\",\"1\",\"0\",\"0\",\"52.29\",\"401\",\"치과\",\"\",\"\",\"\",\"0\",\"0\",\"\",\"\",\"0\",\"\",";

    @Autowired
    ReadLineContext<Hospital> hospitalReadLineContext; //아래코드들처럼 new || getBean을 하지 않아도 SpringBootTest가 Autowired에 적용된 곳에 Bean을 찾아 적용해준다.
    //ReadLineContext<Hospital> hospitalReadLineContext = new ParserFactory().hospitalReadLineContext();
    //ReadLineContext<Hospital> hospitalReadLineContext = hospitalReadLineContext.getBean("hospitalReadLineContest", ReadLineContext<Hospital>);

    @Autowired
    HospitalDao hospitalDao;

    @Test
    @DisplayName("Hospital이 insert가 잘 되는지")
    void add(){
        HospitalParser hp = new HospitalParser();
        Hospital hospital = hp.parse(line1);
        hospitalDao.add(hospital);
    }

    @Test
    @DisplayName("DB 자료 갯수가 잘 출력되는지")
    void getCount(){
        HospitalParser hp = new HospitalParser();
        Hospital hospital = hp.parse(line1);
        hospitalDao.getConut();
        System.out.println(hospitalDao.getConut());
    }


    @Test
    @DisplayName("10만건 이상 데이터가 파싱되는지")
    void oneHundread() throws IOException {
        //서버환경에서 build할때 문제가 생길 수 있습니다.
        //어디에서든지 실행 할 수 있게짜는 것이 목표다.
        String filename = "C:\\Users\\iser\\Desktop\\멋사 교육자료\\자료\\전국병의원정보.csv";
        List<Hospital> hospitalList = hospitalReadLineContext.readByLine(filename);
        System.out.printf("파싱된 갯수: %d",hospitalList.size());
        assertTrue(hospitalList.size() > 10000);
        assertTrue(hospitalList.size() > 100000);
        for (int i = 0; i < 10; i++) {
            System.out.println("i번째 = " + hospitalList.get(i).getHospitalName());
        }
        System.out.printf("파싱된 갯수: %s",hospitalList.size());
    }

    @Test
    @DisplayName("csv 1줄을 Hospital로 잘 만드는지 Test")
    void convertToHospital(){
        HospitalParser hp = new HospitalParser();
        Hospital hospital = hp.parse(line1);
        System.out.println(line1);
        assertEquals(1, hospital.getId()); // col:0
        assertEquals("의원", hospital.getOpenServiceName());//col:1
        assertEquals(3620000,hospital.getOpenLocalGovernmentCode()); // col: 3
        assertEquals("PHMA119993620020041100004",hospital.getManagementNumber()); // col:4
        assertEquals(LocalDateTime.of(1999, 6, 12, 0, 0, 0), hospital.getLicenseData()); //19990612 //col:5
        assertEquals(1, hospital.getBusinessStatus()); //col:7
        assertEquals(13, hospital.getBusinessStatusCode());//col:9
        assertEquals("062-515-2875", hospital.getPhone());//col:17
        assertEquals("광주광역시 북구 풍향동 565번지 4호 3층", hospital.getFullAddress()); //col:18
        assertEquals("광주광역시 북구 동문대로 24, 3층 (풍향동)", hospital.getRoadNameAddress());//col:19
        assertEquals("효치과의원", hospital.getHospitalName());//col:21
        assertEquals("치과의원", hospital.getBusinessTypeName());//col:25
        assertEquals(1, hospital.getHealthcareProviderCount());//col : 30
        assertEquals(0, hospital.getPatientRoomCount()); //col:31
        assertEquals(0, hospital.getTotalNumberOfBeds()); //col:32
        assertEquals(52.29f, hospital.getTotalAreaSize()); //col:33
    }
}