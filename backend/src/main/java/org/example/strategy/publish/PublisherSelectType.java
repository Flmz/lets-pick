package org.example.strategy.publish;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum PublisherSelectType {
    BEST_OF_THE_BEST(0),
    WORST_OF_THE_WORST(),
    ONLY_ONE_CONTENT_EXIST(1),
    ALL;

    private int contentSize;
}
