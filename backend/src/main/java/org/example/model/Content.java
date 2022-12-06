package org.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.model.enums.Category;
import org.example.model.enums.ContentType;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "content")
public class Content {

    @Enumerated(EnumType.STRING)
    ContentType type;
    @NotNull(message = "content url cannot be null")
    String url;
    List<Category> categories = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void addCategory(Category category) {
        categories.add(category);
    }
}
