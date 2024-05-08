package project.financement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.financement.entity.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

}
