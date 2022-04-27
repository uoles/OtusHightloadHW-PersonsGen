package ru.uoles.proj.helper;

import ru.uoles.proj.model.Person;

import java.util.Random;
import java.util.UUID;

/**
 * OtusHightloadHW-PersonsGen
 * Created by IntelliJ IDEA.
 * Developer: Maksim Kulikov
 * Date: 27.04.2022
 * Time: 12:42
 */
public final class PersonGenerateHelper {

    private static final Random random = new Random();

    private PersonGenerateHelper() {
        //...
    }

    public static Person generateNewPerson(String firstName, String secondName, String gender) {
        return new Person(
                getNewGUID(),
                firstName,
                secondName,
                firstName,
                "info",
                getRandomNumber(18, 55),
                gender,
                "City"
        );
    }

    public static String getNewGUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static int getRandomNumber(int min, int max) {
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }
}
