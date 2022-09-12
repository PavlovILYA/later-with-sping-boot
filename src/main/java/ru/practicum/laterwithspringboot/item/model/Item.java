package ru.practicum.laterwithspringboot.item.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.laterwithspringboot.user.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "items")
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(table = "items", name = "user_id")
    @JsonIgnore
    private User user;
    private String url;
    private String description;
    @Column(name = "created_time")
    private LocalDateTime createdTime;
}
