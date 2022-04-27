package ru.uoles.proj.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import ru.uoles.proj.model.BodyName;
import ru.uoles.proj.model.BodySurname;
import org.springframework.stereotype.Repository;
import ru.uoles.proj.utils.FileParseUtil;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * OtusHightloadHW-PersonsGen
 * Created by IntelliJ IDEA.
 * Developer: Maksim Kulikov
 * Date: 26.04.2022
 * Time: 23:48
 */
@Repository
public class InfoDaoImpl implements InfoDao {

    @Override
    public BodyName getNamesList() {
        String json = null;
        try {
            json = FileParseUtil.readFileFromResources("/json/names.json")
                    .replaceAll("\n", "")
                    .replaceAll("\r", "")
                    .replaceAll(" ", "")
                    .trim();
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        return (BodyName) FileParseUtil.parseJson(json, new TypeReference<BodyName>() {} );
    }

    @Override
    public BodySurname getSurnamesList() {
        String json = null;
        try {
            json = FileParseUtil.readFileFromResources("/json/surnames.json")
                    .replaceAll("\n", "")
                    .replaceAll("\r", "")
                    .replaceAll(" ", "")
                    .trim();
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        return (BodySurname) FileParseUtil.parseJson(json, new TypeReference<BodySurname>() {} );
    }
}
