package ru.practicum.laterwithspringboot.item;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.laterwithspringboot.user.User;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByUser(User user);
    Optional<Item> findByIdAndUser(Long itemId, User user);
}
