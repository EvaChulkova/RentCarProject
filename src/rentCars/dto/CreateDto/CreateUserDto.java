package rentCars.dto.CreateDto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateUserDto {
    String firstName;
    String lastName;
    String login;
    String password;
    String role;
}
