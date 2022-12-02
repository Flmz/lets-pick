package org.example.model.enums;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ContentType {
    IMAGE,
    VIDEO;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Category> categories;
}

