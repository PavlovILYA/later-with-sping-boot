package ru.practicum.laterwithspringboot.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.laterwithspringboot.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
