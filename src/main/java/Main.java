import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * fill_db
 * Created by IntelliJ IDEA.
 * Developer: Maksim Kulikov
 * Date: 22.04.2022
 * Time: 16:24
 */
public class Main {

    private final static ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        final BodyName name = parseRusNamesJson();
        final BodySurname surname = parseRusSurnamesJson();

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
    }

    private static BodyName parseRusNamesJson() throws URISyntaxException, IOException {
        String json = FileLoadUtil.readFileFromResources("names.json")
                .replaceAll("\n", "")
                .replaceAll("\r", "")
                .replaceAll(" ", "")
                .trim();

        return (BodyName) parseJson(json,
                new TypeReference<BodyName>() {}
        );
    }

    private static BodySurname parseRusSurnamesJson() throws URISyntaxException, IOException {
        String json = FileLoadUtil.readFileFromResources("surnames.json")
                .replaceAll("\n", "")
                .replaceAll("\r", "")
                .replaceAll(" ", "")
                .trim();

        return (BodySurname) parseJson(json,
                new TypeReference<BodySurname>() {}
        );
    }

    public static Object parseJson(String json, TypeReference object) {
        try {
            return OBJECT_MAPPER.readerFor(object).readValue(json);
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }
}
