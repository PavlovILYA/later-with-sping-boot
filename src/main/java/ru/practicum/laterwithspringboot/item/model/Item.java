package ru.practicum.laterwithspringboot.item.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.laterwithspringboot.user.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "items")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(table = "items", name = "user_id")
    @JsonIgnore
    private User user;
    private String description;
    @ElementCollection
    @CollectionTable(name = "tags", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "name")
    private Set<String> tags = new HashSet<>();
    @Column(name = "normal_url")
    private String normalUrl;
    @Column(name = "resolved_url")
    private String resolvedUrl;
    @Column(name = "mime_type")
    private String mimeType;
    private String title;
    @Column(name = "has_image")
    private boolean hasImage;
    @Column(name = "has_video")
    private boolean hasVideo;
    @Column(name = "resolved_date")
    private LocalDateTime resolvedDate;
}
