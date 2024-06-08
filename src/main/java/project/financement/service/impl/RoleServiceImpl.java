package project.financement.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.financement.entity.Role;
import project.financement.repository.RoleRepository;
import project.financement.service.RoleService;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    public final RoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public Role getDefaultRole() {
        return roleRepository.findByRoleName("ROLE_FreeUser")
                .orElseThrow(() -> new RuntimeException("Default role not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public Role getPremiumRole() {
        return roleRepository.findByRoleName("ROLE_PremiumUser")
                .orElseThrow(() -> new RuntimeException("Premium role not found"));
    }
}
