package ru.uoles.proj.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * fill_db
 * Created by IntelliJ IDEA.
 * Developer: Maksim Kulikov
 * Date: 24.04.2022
 * Time: 15:33
 */
@Data
@EqualsAndHashCode
public class Name {

    private Long id;
    private String name;
    private String sex;
    private Long peoplesCount;
    private String whenPeoplesCount;
    private String source;

    public Name() {
    }

    public Name(Long id, String name, String sex, Long peoplesCount, String whenPeoplesCount, String source) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.peoplesCount = peoplesCount;
        this.whenPeoplesCount = whenPeoplesCount;
        this.source = source;
    }
}
