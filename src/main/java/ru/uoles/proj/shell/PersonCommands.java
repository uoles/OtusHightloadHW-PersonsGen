package ru.uoles.proj.shell;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.uoles.proj.model.BodyName;
import ru.uoles.proj.model.Person;
import ru.uoles.proj.service.PersonService;
import ru.uoles.proj.utils.FileParseUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

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

    @ShellMethod(key = {"generate"}, value = "add persons in db")
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

    @ShellMethod(key = {"generate_batch"}, value = "add batch persons in db")
    public String generateBatch(@ShellOption long nameCount, @ShellOption long surnameCount, @ShellOption int batchSize) {
        Set<Person> listPerson = personService.filterPersonInfo(nameCount, surnameCount);
        log.info("Generated list {} persons", listPerson.size());
        log.info("Batch size {}", batchSize);

        int[][] result = personService.addPersonList(listPerson, batchSize);

        int i = 1;
        for (int[] mas : result) {
            long count = Arrays.stream(mas).filter(o -> Objects.equals(0, o)).count();
            log.info("{}. Batch added - {}", i++, (count > 0 ? "ERROR" : "OK"));
        }

        log.info("=========== All persons added ===========");
        return "Done";
    }

    @ShellMethod(key = {"save_persons_in_jsons"}, value = "save persons in json files")
    public String save_persons_in_jsons(@ShellOption long nameCount, @ShellOption long surnameCount, @ShellOption int jsonSize, @ShellOption String path) {
        Set<Person> listPerson = personService.filterPersonInfo(nameCount, surnameCount);
        log.info("Generated list {} persons", listPerson.size());
        log.info("Objects count in json {}", jsonSize);

        List<List<Person>> lists = ListUtils.partition(new ArrayList<>(listPerson), jsonSize);
        int i=1;
        for (List<Person> list : lists) {
            String fileName = FileParseUtil.createNewFile(path, list, i);
            log.info("{}. File created - {}", i, fileName);
            i++;
        }

        log.info("=========== All persons saved in json ===========");
        return "Done";
    }

    @ShellMethod(key = {"add_persons_from_jsons"}, value = "add persons from json files to db")
    public String add_persons_from_jsons(@ShellOption String path) {
        List<String> list = FileParseUtil.listFilesForFolder(new File(path));
        log.info("Read list jsons - {}", list.size());

        int i = 1;
        for (String fileName : list) {
            log.info("{} ==========================================", i++);
            File file = new File(fileName);

            List<Person> persons = (List<Person>) FileParseUtil.parseJsonFile(file, new TypeReference<List<Person>>() {} );
            log.info("File read - {}", fileName);

            int[][] result = personService.addPersonList(new HashSet<>(persons), persons.size());

            for (int[] mas : result) {
                long count = Arrays.stream(mas).filter(o -> Objects.equals(0, o)).count();
                log.info("Batch added - {}", (count > 0 ? "ERROR" : "OK"));
            }

            try {
                Files.deleteIfExists(file.toPath());
                log.info("File deleted - {}", fileName);
            } catch (IOException e) {
                log.error("Error delete file '{}'", fileName, e);
            }
        }

        log.info("=========== All read from jsons ===========");
        return "Done";
    }
}