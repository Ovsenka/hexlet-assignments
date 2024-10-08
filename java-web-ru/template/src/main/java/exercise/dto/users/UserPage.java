package exercise.dto.users;

import lombok.AllArgsConstructor;
import lombok.Getter;

// BEGIN
@Getter
@AllArgsConstructor
public class UserPage {
    private long id;
    private String firstname;
    private String lastname;
    private String email;
}
// END
