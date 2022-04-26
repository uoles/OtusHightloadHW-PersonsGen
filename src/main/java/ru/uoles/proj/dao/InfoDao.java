package ru.uoles.proj.dao;

import ru.uoles.proj.model.BodyName;
import ru.uoles.proj.model.BodySurname;

/**
 * OtusHightloadHW-PersonsGen
 * Created by IntelliJ IDEA.
 * Developer: Maksim Kulikov
 * Date: 26.04.2022
 * Time: 23:47
 */
public interface InfoDao {

    BodyName getNamesList();

    BodySurname getSurnamesList();
}
