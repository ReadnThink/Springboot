package com.Springboot.practice.controller;

import com.Springboot.practice.dao.UserDao;
import com.Springboot.practice.domain.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-api")
@Slf4j
public class UserController {
    @Autowired
    UserDao userDao;

    @PostMapping(value = "/user")
    public UserDto post(@RequestBody UserDto userDto){
        userDao.add(userDto);
        log.info("정보가 추가되었습니다.\n" + userDto);
        return userDto;
    }
    @DeleteMapping(value = "/{id}")
    public String deleteById(@PathVariable String id){
        userDao.deleteById(id);
        return "정보가 삭제되었습니다 :" + id;
    }

    @DeleteMapping(value = "/user1")
    public String deleteAll(){
        userDao.deleteAll();
        return "모든 데이터가 삭제되었습니다.";
    }


}
