package com.Springboot.practice.controller;


import com.Springboot.practice.dao.HospitalDao;
import com.Springboot.practice.dao.UserDao;
import com.Springboot.practice.domain.dto.Hospital;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/Hospital-api")
@Slf4j
public class HospitalController {
    @Autowired
    HospitalDao hospitalDao;

    @GetMapping(value = "/hello")
    public String hello(){
        return "hello";
    }

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
    public Hospital information(@RequestParam int id){
        Hospital hospital = new Hospital();
        hospital = hospitalDao.findById(id);
        return hospital;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hospital> get(@PathVariable Integer id){
        Hospital hospital = hospitalDao.findById(id);
        Optional<Hospital> opt = Optional.of(hospital);
        if(!opt.isEmpty()){
            return  ResponseEntity.ok().body(hospital);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Hospital());
        }
    }
}
