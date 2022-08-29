package ru.practicum.laterwithspringboot.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();
    User save(User user);
    void delete(long id);
    Optional<User> findById(long id);
}
