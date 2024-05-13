package project.financement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.financement.entity.Role;
import project.financement.entity.User;
import project.financement.exception.UserNotFoundException;
import project.financement.repository.RoleRepository;
import project.financement.repository.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public void upgradeUserToPremium(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException(userId));
        Role premiumRole = roleRepository.findByRoleName("PremiumUser").orElseThrow(() ->
                new RuntimeException("Premium role not found"));
        user.getUserInfo().setRole(premiumRole);
        userRepository.save(user);
    }
}
