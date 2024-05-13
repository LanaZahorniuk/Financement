package project.financement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.financement.entity.Role;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByRoleName(String roleName);
}

