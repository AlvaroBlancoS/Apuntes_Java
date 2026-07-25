package springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType {

    USER("USER"),
    ADMIN("ADMIN");

    private final String roleName;

    public static RoleType findRoleType(String roleName) {
        for (RoleType role : RoleType.values()) {
            if (role.getRoleName().equalsIgnoreCase(roleName)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid role name: " + roleName);
    }
    
}
