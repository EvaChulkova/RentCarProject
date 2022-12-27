package rentCars.mapper;

import rentCars.dto.UserDto;
import rentCars.entity.User;

public class UserMapper implements Mapper<User, UserDto>{
    private static final UserMapper INSTANCE = new UserMapper();
    @Override
    public UserDto mapFrom(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .login(user.getLogin())
                .role(user.getRole())
                .build();
    }

    public static UserMapper getInstance() {
        return INSTANCE;
    }
}
