package ru.uoles.proj.service;

import ru.uoles.proj.dao.InfoDao;
import lombok.RequiredArgsConstructor;
import ru.uoles.proj.model.BodyName;
import ru.uoles.proj.model.BodySurname;
import ru.uoles.proj.model.Person;
import ru.uoles.proj.model.Surname;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * OtusHightloadHW-PersonsGen
 * Created by IntelliJ IDEA.
 * Developer: Maksim Kulikov
 * Date: 26.04.2022
 * Time: 23:51
 */
@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService<Person> {

    private final InfoDao infoDao;

    @Override
    public Set<Person> filterPersonInfo() {
        final BodyName name = infoDao.getNamesList();
        final BodySurname surname = infoDao.getSurnamesList();

        Set<Surname> womanSurnames = surname.getBody().stream()
                .filter(o -> o.getSurname().endsWith("а") || o.getSurname().endsWith("ая"))
                .limit(1000)
                .collect(Collectors.toSet());
        System.out.println("List of womanSurnames: " + womanSurnames.size());

        Set<Surname> manSurnames = surname.getBody().stream()
                .filter(o -> !(o.getSurname().endsWith("а") || o.getSurname().endsWith("ая")))
                .limit(1000)
                .collect(Collectors.toSet());
        System.out.println("List of manSurnames: " + manSurnames.size());

        Set<Person> result = new HashSet<>();
        for (Surname value: manSurnames) {
            Set<Person> personMan = name.getBody().stream()
                    .filter(o -> o.getSex().contains("М"))
                    .map(o -> new Person(o.getName(), value.getSurname(), o.getSex()))
                    .limit(1000)
                    .collect(Collectors.toSet());
            result.addAll(personMan);
        }

        for (Surname value: womanSurnames) {
            Set<Person> personWoman = name.getBody().stream()
                    .filter(o -> o.getSex().contains("Ж"))
                    .map(o -> new Person(o.getName(), value.getSurname(), o.getSex()))
                    .limit(1000)
                    .collect(Collectors.toSet());
            result.addAll(personWoman);
        }

        System.out.println("List of result: " + result.size());
        return result;
    }
}
