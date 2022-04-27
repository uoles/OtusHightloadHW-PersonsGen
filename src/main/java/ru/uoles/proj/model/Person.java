package ru.uoles.proj.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * fill_db
 * Created by IntelliJ IDEA.
 * Developer: Maksim Kulikov
 * Date: 22.04.2022
 * Time: 16:27
 */
@Data
@ToString
@EqualsAndHashCode
public class Person {

    private String guid;
    private Date registrationDate;
    private Date dissolutionDate;
    private int permissionsId;
    private String firstName;
    private String secondName;
    private String nickName;
    private String info;
    private int age;
    private String gender;
    private String town;
    private String operation;

    public Person() {
    }

    public Person(String guid, String firstName, String secondName, String nickName, String info, int age, String gender, String town) {
        this.guid = guid;
        this.firstName = firstName;
        this.secondName = secondName;
        this.nickName = nickName;
        this.info = info;
        this.age = age;
        this.gender = gender;
        this.town = town;
    }
}
