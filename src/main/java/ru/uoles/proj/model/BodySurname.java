package ru.uoles.proj.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * fill_db
 * Created by IntelliJ IDEA.
 * Developer: Maksim Kulikov
 * Date: 24.04.2022
 * Time: 15:43
 */
@Data
@EqualsAndHashCode
public class BodySurname {

    private Set<Surname> body;

    public BodySurname() {
    }

    public BodySurname(Set<Surname> body) {
        this.body = body;
    }
}
