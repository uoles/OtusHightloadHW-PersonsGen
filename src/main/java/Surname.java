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
public class Surname {

    private Long id;
    private String surname;
    private String sex;
    private Long peoplesCount;
    private String whenPeoplesCount;
    private String source;

    public Surname() {
    }

    public Surname(Long id, String surname, String sex, Long peoplesCount, String whenPeoplesCount, String source) {
        this.id = id;
        this.surname = surname;
        this.sex = sex;
        this.peoplesCount = peoplesCount;
        this.whenPeoplesCount = whenPeoplesCount;
        this.source = source;
    }
}
