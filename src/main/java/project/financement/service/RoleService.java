package project.financement.service;

import project.financement.entity.Role;

public interface RoleService {

    Role getDefaultRole();

    Role getPremiumRole();
}
