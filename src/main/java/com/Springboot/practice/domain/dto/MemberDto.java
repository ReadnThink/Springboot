package com.Springboot.practice.domain.dto;

public class MemberDto {
    private String name;
    private String goal;
    private String how;

    public MemberDto(String name, String goal, String how) {
        this.name = name;
        this.goal = goal;
        this.how = how;
    }

    public String getName() {
        return name;
    }

    public String getGoal() {
        return goal;
    }

    public String getHow() {
        return how;
    }

    @Override
    public String toString(){
        return this.name + " " + this.goal + " " + this.how;
    }
}
