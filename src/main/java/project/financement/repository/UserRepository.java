package project.financement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.financement.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.userInfo ui LEFT JOIN FETCH ui.accounts WHERE u.userId = :userId")
    Optional<User> findUserById(@Param("userId") UUID userId);
}
