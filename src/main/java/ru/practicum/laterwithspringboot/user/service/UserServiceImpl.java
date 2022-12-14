package ru.practicum.laterwithspringboot.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.laterwithspringboot.exception.UserNotFoundException;
import ru.practicum.laterwithspringboot.user.UserMapper;
import ru.practicum.laterwithspringboot.user.model.User;
import ru.practicum.laterwithspringboot.user.model.UserCreateDto;
import ru.practicum.laterwithspringboot.user.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(UserCreateDto userCreateDto) {
        User user = UserMapper.toItem(userCreateDto);
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> {
                throw new UserNotFoundException(userId);
        });
    }
}
