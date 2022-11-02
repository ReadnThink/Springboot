package com.Springboot.practice.service;

import com.Springboot.practice.dao.HospitalDao;
import com.Springboot.practice.domain.dto.Hospital;
import com.Springboot.practice.parser.ReadLineContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class HopitalService {
    //@Autowired 대신 private final을 해주었다.
    private final ReadLineContext<Hospital> hospitalReadLineContext;
    private final HospitalDao hospitalDao;

    public HopitalService(ReadLineContext<Hospital> hospitalReadLineContext, HospitalDao hospitalDao) {
        this.hospitalReadLineContext = hospitalReadLineContext;
        this.hospitalDao = hospitalDao;
    }

      //병렬처리 하지 않은것
//    @Transactional
//    public int insertLargeVolumeHospitalData(String filename) throws IOException {
//        int cnt = 0;
//        //57분 시작
//        try {
//            List<Hospital> hospitalList = hospitalReadLineContext.readByLine(filename);
//            System.out.println("파싱이 끝났습니다.");
//            for (Hospital hospital : hospitalList) { //loop구간
//                try {
//                    this.hospitalDao.add(hospital); //db에 insert구간
//                    cnt++;
//                } catch (Exception e) {
//                    System.out.printf("id:%d 레코드에 문제가 있습니다.", hospital.getId());
//                    throw new RuntimeException(e);
//                }
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return cnt;
//    }

    //병렬처리 코드
    @Transactional
    //2시 8분시작
    public int insertLargeVolumeHospitalData(String filename) throws IOException {
        List<Hospital> hospitalList;
        try {
            hospitalList = hospitalReadLineContext.readByLine(filename);
            System.out.println("파싱이 끝났습니다.");
            hospitalList.stream().parallel()
                    .forEach(hospital -> {
                        try {
                            this.hospitalDao.add(hospital);
                        } catch (Exception e) {
                            System.out.printf("id:%d 레코드에문제가 있습니다.\n",hospital.getId());
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (!Optional.of(hospitalList).isEmpty()) {
            return hospitalList.size();
        } else {
            return 0;
        }
    }
}
