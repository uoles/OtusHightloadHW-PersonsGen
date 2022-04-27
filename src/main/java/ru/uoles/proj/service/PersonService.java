package ru.uoles.proj.service;

import ru.uoles.proj.model.Person;

import java.util.Set;

/**
 * OtusHightloadHW-PersonsGen
 * Created by IntelliJ IDEA.
 * Developer: Maksim Kulikov
 * Date: 26.04.2022
 * Time: 23:51
 */
public interface PersonService<Person> {

    Set<Person> filterPersonInfo(long nameCount, long surnameCount);

    void addPerson(Person person);

    int[][] addPersonList(Set<Person> person, int batchSize);
}
