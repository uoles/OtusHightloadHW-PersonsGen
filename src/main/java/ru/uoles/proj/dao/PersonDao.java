package ru.uoles.proj.dao;

import java.util.Set;

/**
 * OtusHightloadHW-PersonsGen
 * Created by IntelliJ IDEA.
 * Developer: Maksim Kulikov
 * Date: 27.04.2022
 * Time: 12:01
 */
public interface PersonDao<Person> {

    void addPerson(Person person);

    int[][] addPersonList(final Set<Person> personList, int batchSize);
}
