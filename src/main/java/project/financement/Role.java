package project.financement;

import java.util.Set;
import java.util.UUID;

public class Role {
    private UUID roleId;
    private String roleName;
    private Set<Authority> authoritySet;
}
