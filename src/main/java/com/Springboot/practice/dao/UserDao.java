package com.Springboot.practice.dao;

import com.Springboot.practice.domain.dto.UserDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDao {
    JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void deleteAll(){
        this.jdbcTemplate.update("delete from users");
    }

    public void deleteById(String id){
        this.jdbcTemplate.update("delete from users where id = ?",id);
    }

    public void add(UserDto userDto){
        this.jdbcTemplate.update("insert into users(id, name, password) values(?,?,?)", userDto.getId(), userDto.getName(), userDto.getPassword());
    }
}
