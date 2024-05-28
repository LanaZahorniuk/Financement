package project.financement.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import project.financement.dto.UserAfterCreationDto;
import project.financement.dto.UserCreateDto;
import project.financement.entity.Role;
import project.financement.entity.User;
import project.financement.entity.UserInfo;
import project.financement.exception.UserNotFoundException;
import project.financement.mapper.UserMapper;
import project.financement.repository.UserInfoRepository;
import project.financement.repository.UserRepository;
import project.financement.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final UserMapper userMapper;
    private final RoleServiceImpl roleService;

    @Override
    @Transactional
    public UserAfterCreationDto getUserById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException(id));
        return userMapper.toDto(user);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public UserAfterCreationDto createUser(UserCreateDto newUserDto) {
        User user = userMapper.toEntity(newUserDto);
        user.setRegistrationDate(LocalDate.from(LocalDateTime.now()));
        Role defaultRole = roleService.getDefaultRole();
        user.getUserInfo().setRole(defaultRole);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    @Transactional
    public UserAfterCreationDto updateUserEmail(UUID id, String newEmail) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException(id));
        UserInfo userInfo = user.getUserInfo();
        userInfo.setEmail(newEmail);
        userInfoRepository.save(userInfo);
        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public UserAfterCreationDto updateUserPassword(UUID id, String newPassword) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException(id));
        UserInfo userInfo = user.getUserInfo();
        userInfo.setPassword(newPassword);
        userInfoRepository.save(userInfo);
        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public UserAfterCreationDto updateUserPhoneNumber(UUID id, String newPhoneNumber) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException(id));
        UserInfo userInfo = user.getUserInfo();
        userInfo.setPhoneNumber(newPhoneNumber);
        userInfoRepository.save(userInfo);
        return userMapper.toDto(user);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String deleteUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException(id));
        userRepository.delete(user);
        return "User deleted successfully.";
    }
}
