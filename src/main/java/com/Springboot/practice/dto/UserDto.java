package com.Springboot.practice.dto;

public class UserDto {
    private String id;
    private String name;
    private String password;

    public UserDto(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
    @Override
    public String toString(){
        return this.id + " " + this.name + " " + this.password;
    }
}
