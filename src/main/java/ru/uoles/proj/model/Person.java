package ru.uoles.proj.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * fill_db
 * Created by IntelliJ IDEA.
 * Developer: Maksim Kulikov
 * Date: 22.04.2022
 * Time: 16:27
 */
@Data
@EqualsAndHashCode
public class Person {

    private String firstName;
    private String secondName;
    private String sex;

    public Person(String firstName, String secondName, String sex) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.sex = sex;
    }
}
