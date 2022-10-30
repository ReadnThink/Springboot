package com.Springboot.practice.controller;

import com.Springboot.practice.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("put-api")
@Slf4j
public class PutController {
    @PutMapping(value = "/member")
    public String put(@RequestBody Map<String, Object> putData){
        StringBuilder sb = new StringBuilder();

        putData.entrySet().forEach(map-> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });
        log.info("\n"+sb.toString());
        return sb.toString();
    }

    @PutMapping(value = "/member1")
    public String put1(@RequestBody MemberDto memberDto){
        log.info("\n"+memberDto.toString());
        return memberDto.toString();
    }
    @PutMapping(value = "/member2")
    public MemberDto put2(@RequestBody MemberDto memberDto){
        log.info("\n"+memberDto);
        return memberDto;
    }

    @PutMapping(value = "/member3")
    public ResponseEntity put3(@RequestBody MemberDto memberDto){
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(memberDto);
    }
}
