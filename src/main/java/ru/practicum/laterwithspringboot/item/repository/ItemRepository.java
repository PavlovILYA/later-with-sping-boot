package ru.practicum.laterwithspringboot.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.laterwithspringboot.item.model.Item;
import ru.practicum.laterwithspringboot.user.model.User;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long>, CheckUrlItemRepository {
    List<Item> findAllByUser(User user);
    Optional<Item> findByIdAndUser(Long itemId, User user);
}
