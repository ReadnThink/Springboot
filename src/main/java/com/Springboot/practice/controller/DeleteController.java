package com.Springboot.practice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delete-api")
@Slf4j
public class DeleteController {
    @DeleteMapping(value = "/{variable}")
    public String delete(@PathVariable String variable){
        return variable;
    }
}
