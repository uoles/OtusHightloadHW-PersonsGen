package ru.uoles.proj.service;

import lombok.extern.slf4j.Slf4j;
import ru.uoles.proj.dao.InfoDao;
import lombok.RequiredArgsConstructor;
import ru.uoles.proj.dao.PersonDao;
import ru.uoles.proj.model.BodyName;
import ru.uoles.proj.model.BodySurname;
import ru.uoles.proj.model.Person;
import ru.uoles.proj.model.Surname;
import org.springframework.stereotype.Service;
import ru.uoles.proj.helper.PersonGenerateHelper;

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
@Slf4j
@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService<Person> {

    private final InfoDao infoDao;
    private final PersonDao<Person> personDao;

    @Override
    public Set<Person> filterPersonInfo(long nameCount, long surnameCount) {
        final BodyName name = infoDao.getNamesList();
        final BodySurname surname = infoDao.getSurnamesList();

        Set<Surname> womanSurnames = surname.getBody().stream()
                .filter(o -> o.getSurname().endsWith("а") || o.getSurname().endsWith("ая"))
                .limit(surnameCount)
                .collect(Collectors.toSet());
        log.info("List of womanSurnames: {}", womanSurnames.size());

        Set<Surname> manSurnames = surname.getBody().stream()
                .filter(o -> !(o.getSurname().endsWith("а") || o.getSurname().endsWith("ая")))
                .limit(surnameCount)
                .collect(Collectors.toSet());
        log.info("List of manSurnames: {}", manSurnames.size());

        Set<Person> result = new HashSet<>();
        for (Surname value: manSurnames) {
            Set<Person> personMan = name.getBody().stream()
                    .filter(o -> o.getSex().contains("М"))
                    .map(o -> PersonGenerateHelper.generateNewPerson(o.getName(), value.getSurname(), o.getSex()))
                    .limit(nameCount)
                    .collect(Collectors.toSet());
            result.addAll(personMan);
        }

        for (Surname value: womanSurnames) {
            Set<Person> personWoman = name.getBody().stream()
                    .filter(o -> o.getSex().contains("Ж"))
                    .map(o -> PersonGenerateHelper.generateNewPerson(o.getName(), value.getSurname(), o.getSex()))
                    .limit(nameCount)
                    .collect(Collectors.toSet());
            result.addAll(personWoman);
        }

        log.info("List of result: {}", result.size());
        return result;
    }

    @Override
    public void addPerson(Person person) {
        personDao.addPerson(person);
    }
}
