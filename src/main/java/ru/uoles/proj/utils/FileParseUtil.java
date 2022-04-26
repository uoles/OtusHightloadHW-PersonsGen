package ru.uoles.proj.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

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
        URL resource = FileParseUtil.class.getClassLoader().getResource(fileName);
        byte[] bytes = Files.readAllBytes(Paths.get(resource.toURI()));
        return new String(bytes);
    }

    public static Object parseJson(String json, TypeReference object) {
        try {
            return OBJECT_MAPPER.readerFor(object).readValue(json);
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }
}
