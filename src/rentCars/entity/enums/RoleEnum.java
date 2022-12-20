package rentCars.entity.enums;

import java.util.Arrays;
import java.util.Optional;

public enum RoleEnum {
    ADMIN,
    CLIENT;

    private static final RoleEnum[] VALUES = values();
    public static Optional<RoleEnum> find(String role) {
        return Arrays.stream(VALUES)
                .filter(it -> it.name().equals(role))
                .findFirst();
    }
}
