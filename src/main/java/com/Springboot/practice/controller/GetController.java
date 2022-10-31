package com.Springboot.practice.controller;

import com.Springboot.practice.domain.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/get-api")
@Slf4j
public class GetController {
    @GetMapping(value = "/get")
    public String get(){
        return "get";
    }

    @GetMapping(value = "/variable/{variable}")
    public String variable(@PathVariable String variable){
        log.info("variable : {}",variable);
        return variable;
    }

    @GetMapping(value = "/variable1")
    public String variable1(@RequestParam String name,
                            @RequestParam String goal,
                            @RequestParam String how){
        String show = name + " " + goal + " " + how;
        log.info("variable : {}",show);
        return show;
    }
    @GetMapping(value = "/variable2")
    public String variable2(@RequestParam Map<String, Object> getData){
        StringBuilder sb = new StringBuilder();

        getData.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });

        log.info("\n" + "variable : {}",sb.toString());
        return sb.toString();
    }

    @GetMapping(value = "/variable3")
    public String variable3(MemberDto memberDto){
        return memberDto.toString();
    }
}
