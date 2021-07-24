package com.mongodb.example.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @Description 学校实体
 * @Author lktbz
 * @Date 2021/07/24
 */
@Document("school")
public class School {
    private String name;
    private String levelType;
    private List<Person> personList;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevelType() {
        return levelType;
    }

    public void setLevelType(String levelType) {
        this.levelType = levelType;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", levelType='" + levelType + '\'' +
                ", personList=" + personList +
                ", address='" + address + '\'' +
                '}';
    }

    public School() {
    }

    public School(String name, String levelType, List<Person> personList, String address) {
        this.name = name;
        this.levelType = levelType;
        this.personList = personList;
        this.address = address;
    }
}
