package com.wellbeeing.wellbeeing.domain.message.sport;

import com.wellbeeing.wellbeeing.domain.sport.Exercise;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class AddExerciseWithLabelsRequest {
    private Exercise exercise;
    private List<Long> labelsIds;
}
