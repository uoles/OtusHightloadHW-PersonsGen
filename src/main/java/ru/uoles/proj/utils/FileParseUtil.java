package ru.uoles.proj.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import ru.uoles.proj.Application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
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
}
