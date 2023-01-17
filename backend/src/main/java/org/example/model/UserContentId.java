package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@Data
@AllArgsConstructor
public class UserContentId implements Serializable {
    private Long userId;

    private Long contentId;

}
