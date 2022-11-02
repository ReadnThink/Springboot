package com.Springboot.practice.controller;


import com.Springboot.practice.dao.HospitalDao;
import com.Springboot.practice.dao.UserDao;
import com.Springboot.practice.domain.dto.Hospital;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Hospital-api")
@Slf4j
public class HospitalController {
    @Autowired
    HospitalDao hospitalDao;

    @PostMapping(value = "/hospital")
    public Hospital post(@RequestBody Hospital hospital){
        hospitalDao.add(hospital);
        log.info("정보가 추가되었습니다\n"+ hospital);
        return hospital;
    }

    @DeleteMapping(value = "/hospital1")
    public String deleteAll(){
        hospitalDao.deleteAll();
        return "모든 데이터가 삭제되었습니다.";
    }

    @GetMapping(value = "/information")
    public String information(@RequestParam int id){
        hospitalDao.findById(id);
        return "id값이 일치하는 정보가 호출되었습니다.";
    }
}
