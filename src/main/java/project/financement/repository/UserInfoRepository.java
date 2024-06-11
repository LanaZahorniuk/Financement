package project.financement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.financement.entity.UserInfo;

import java.util.UUID;

public interface UserInfoRepository extends JpaRepository<UserInfo, UUID> {
    UserInfo findByUsername(String username);
}
