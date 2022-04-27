package ru.uoles.proj.shell;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.uoles.proj.model.Person;
import ru.uoles.proj.service.PersonService;

import java.util.Set;

/**
 * OtusHightloadHW-PersonsGen
 * Created by IntelliJ IDEA.
 * Developer: Maksim Kulikov
 * Date: 27.04.2022
 * Time: 16:18
 */
@Slf4j
@ShellComponent
@RequiredArgsConstructor
public class PersonCommands {

    private final PersonService<Person> personService;

    @ShellMethod(key = {"generate"}, value = "generate persons")
    public String generate(@ShellOption long nameCount, @ShellOption long surnameCount) {
        Set<Person> listPerson = personService.filterPersonInfo(nameCount, surnameCount);
        log.info("Generated list '{}' persons", listPerson.size());

        int i = 1;
        for (Person person : listPerson) {
            personService.addPerson(person);
            log.info("{}. Add {}", i++, person.toString());
        }

        log.info("=========== All persons added ===========");
        return "Done";
    }
}