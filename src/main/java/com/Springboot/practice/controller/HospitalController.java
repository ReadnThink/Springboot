package com.Springboot.practice.controller;


import com.Springboot.practice.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Hospital-api")
@Slf4j
public class HospitalController {
    @Autowired
    UserDao userDao;

}
