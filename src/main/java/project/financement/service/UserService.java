package project.financement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.financement.entity.User;
import project.financement.entity.UserInfo;
import project.financement.exception.UserNotFoundException;
import project.financement.repository.UserInfoRepository;
import project.financement.repository.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;

    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException(id));
    }

    @Transactional
    public User createUser(User newUser) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(newUser.getUserInfo().getUsername());
        userInfo.setEmail(newUser.getUserInfo().getEmail());
        userInfo.setPassword(newUser.getUserInfo().getPassword());
        userInfo.setPhoneNumber(newUser.getUserInfo().getPhoneNumber());

        userInfoRepository.save(userInfo);

        User user = new User();
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setDateOfBirth(newUser.getDateOfBirth());
        user.setUserInfo(userInfo);

        userRepository.save(user);
        return user;
    }

    @Transactional
    public User updateUserEmail(UUID id, String newEmail) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException(id));
        UserInfo userInfo = user.getUserInfo();
        userInfo.setEmail(newEmail);
        userInfoRepository.save(userInfo);
        return user;
    }

    @Transactional
    public User updateUserPassword(UUID id, String newPassword) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException(id));
        UserInfo userInfo = user.getUserInfo();
        userInfo.setPassword(newPassword);
        userInfoRepository.save(userInfo);
        return user;
    }

    @Transactional
    public User updateUserPhoneNumber(UUID id, String newPhoneNumber) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException(id));
        UserInfo userInfo = user.getUserInfo();
        userInfo.setPhoneNumber(newPhoneNumber);
        userInfoRepository.save(userInfo);
        return user;
    }

    public String deleteUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException(id));
        userRepository.delete(user);
        return "User deleted successfully.";
    }
}
