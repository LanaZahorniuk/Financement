package project.financement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.financement.entity.Account;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
}
