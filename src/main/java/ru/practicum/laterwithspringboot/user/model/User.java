package ru.practicum.laterwithspringboot.user.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.laterwithspringboot.item.model.Item;

import javax.persistence.*;
import java.time.LocalDate;
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
    @Column(name = "registration_date")
    LocalDate registrationDate;
    @OneToMany(mappedBy = "user")
    List<Item> items = new ArrayList<>();
}
