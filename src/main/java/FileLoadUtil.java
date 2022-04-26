import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Класс для загрузки файлов с данными для тестов.
 */
public final class FileLoadUtil {

    private FileLoadUtil() {
    }

    /**
     * Чтение файлов из ресурсов.
     *
     * @param fileName  имя файла.
     * @return          файл в формате String.
     * @throws URISyntaxException
     * @throws IOException
     */
    public static String readFileFromResources(String fileName) throws URISyntaxException, IOException {
        URL resource = FileLoadUtil.class.getClassLoader().getResource(fileName);
        byte[] bytes = Files.readAllBytes(Paths.get(resource.toURI()));
        return new String(bytes);
    }

    /**
     * Чтение файлов из ресурсов.
     *
     * @param fileName  имя файла.
     * @return          файл в формате String.
     */
    public static String read(String fileName) {
        String json;
        try {
            json = readFileFromResources(fileName);
        } catch (Exception e) {
            e.printStackTrace();
            json = e.getMessage();
        }
        return json;
    }
}
