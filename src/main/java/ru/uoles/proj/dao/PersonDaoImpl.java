package ru.uoles.proj.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.uoles.proj.model.Person;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * OtusHightloadHW-PersonsGen
 * Created by IntelliJ IDEA.
 * Developer: Maksim Kulikov
 * Date: 27.04.2022
 * Time: 12:01
 */
@Repository
@RequiredArgsConstructor
@PropertySource(name="sqlPerson", value="classpath:/db/sql/person.xml")
public class PersonDaoImpl implements PersonDao<Person> {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${add.person}")
    private String ADD_PERSON;

    @Value("${add.person.batch}")
    private String ADD_PERSON_BATCH;

    @Override
    public void addPerson(final Person person) {
        namedParameterJdbcTemplate.update(
                ADD_PERSON,
                personToParams(person)
        );
    }

    @Override
    public int[][] addPersonList(final Set<Person> personList, int batchSize) {
        return namedParameterJdbcTemplate.getJdbcTemplate().batchUpdate(
                ADD_PERSON_BATCH,
                personList,
                batchSize,
                (ps, person) -> {
                    ps.setString(1, person.getGuid());
                    ps.setString(2, person.getFirstName());
                    ps.setString(3, person.getSecondName());
                    ps.setString(4, person.getNickName());
                    ps.setString(5, person.getInfo());
                    ps.setInt(6, person.getAge());
                    ps.setString(7, person.getGender());
                    ps.setString(8, person.getTown());
                });
    }

    private Map<String, Object> personToParams(final Person person) {
        Map<String, Object> params = new HashMap<>();
        params.put("guid", person.getGuid());
        params.put("permissions_id", person.getPermissionsId());
        params.put("first_name", person.getFirstName());
        params.put("second_name", person.getSecondName());
        params.put("nick_name", person.getNickName());
        params.put("info", person.getInfo());
        params.put("age", person.getAge());
        params.put("gender", person.getGender());
        params.put("town", person.getTown());
        return params;
    }
}
