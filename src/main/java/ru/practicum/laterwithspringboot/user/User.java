package ru.practicum.laterwithspringboot.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.laterwithspringboot.item.Item;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String email;
    String name;
    @OneToMany(mappedBy = "user")
    List<Item> items = new ArrayList<>();
}
