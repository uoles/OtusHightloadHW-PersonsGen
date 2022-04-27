package ru.uoles.proj.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.util.Strings;
import org.springframework.core.io.ClassPathResource;
import ru.uoles.proj.Application;
import ru.uoles.proj.model.Person;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * OtusHightloadHW-PersonsGen
 * Created by IntelliJ IDEA.
 * Developer: Maksim Kulikov
 * Date: 26.04.2022
 * Time: 23:51
 */
public final class FileParseUtil {

    private final static ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    private FileParseUtil() {
        //...
    }

    public static String readFileFromResources(String fileName) throws URISyntaxException, IOException {
        String result = null;
        try (
           InputStream inputStream = Application.class.getResourceAsStream(fileName);
           BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))
        ) {
            result = reader.lines().collect(Collectors.joining(System.lineSeparator()));
        }
        return result;
    }

    public static Object parseJson(String json, TypeReference object) {
        try {
            return OBJECT_MAPPER.readerFor(object).readValue(json);
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }

    public static Object parseJsonFile(File file, TypeReference object) {
        try {
            return OBJECT_MAPPER.readValue(file, object);
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }

    public static String createNewFile(final String path, final List<Person> persons, int number) {
        String fileName = String.join("", path, "/persons", String.valueOf(number), ".json");
        try {
            OBJECT_MAPPER.writeValue(new File(fileName), persons);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    public static List<String> listFilesForFolder(final File folder) {
        List<String> files = new ArrayList<>();
        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            files.add(fileEntry.getAbsolutePath());
        }
        return files;
    }
}
