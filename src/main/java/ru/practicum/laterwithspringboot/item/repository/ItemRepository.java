package ru.practicum.laterwithspringboot.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.practicum.laterwithspringboot.item.model.Item;
import ru.practicum.laterwithspringboot.user.model.User;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long>,
                                        CheckUrlItemRepository,
                                        QuerydslPredicateExecutor<Item> {
    Optional<Item> findByIdAndUser(Long itemId, User user);
}
