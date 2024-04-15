package project.financement.entity;

import java.util.Set;
import java.util.UUID;

public class Authority {
    private UUID authorityId;
    private String authorityName;
    private Set<Role> roles;
}
