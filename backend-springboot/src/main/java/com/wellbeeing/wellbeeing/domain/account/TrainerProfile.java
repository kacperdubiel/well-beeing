package com.wellbeeing.wellbeeing.domain.account;
import com.wellbeeing.wellbeeing.domain.sport.TrainingPlan;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@NoArgsConstructor
@Entity
public class TrainerProfile extends SpecialistProfile {

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    private Set<TrainingPlan> createdTrainingPlans = new HashSet<>();

    public TrainerProfile(Profile userProfile) {
        super(userProfile);
    }
}
