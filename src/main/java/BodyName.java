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
public class BodyName {

    private Set<Name> body;

    public BodyName() {
    }

    public BodyName(Set<Name> body) {
        this.body = body;
    }
}
