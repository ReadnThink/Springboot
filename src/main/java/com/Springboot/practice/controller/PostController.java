package com.Springboot.practice.controller;

import com.Springboot.practice.domain.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("post-api")
@Slf4j
public class PostController {
    @PostMapping(value = "/member")
    public String post(@RequestBody Map<String, Object> postData){
        StringBuilder sb = new StringBuilder();

        postData.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });
        log.info("\n"+sb.toString());
        return sb.toString();
    }

    @PostMapping(value = "/member2")
    public String post1(@RequestBody MemberDto memberDto){
        log.info("\n"+memberDto.toString());
        return memberDto.toString();
    }

    @PostMapping(value = "/member3")
    public MemberDto post2(@RequestBody MemberDto memberDto){
        log.info("\n"+memberDto);
        return memberDto;
    }
}
