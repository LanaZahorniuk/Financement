package project.financement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.financement.entity.Role;
import project.financement.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {

    public final RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public Role getDefaultRole() {
        return roleRepository.findByRoleName("FreeUser")
                .orElseThrow(() -> new RuntimeException("Default role not found"));
    }

    @Transactional(readOnly = true)
    public Role getPremiumRole() {
        return roleRepository.findByRoleName("PremiumUser")
                .orElseThrow(() -> new RuntimeException("Premium role not found"));
    }
}
